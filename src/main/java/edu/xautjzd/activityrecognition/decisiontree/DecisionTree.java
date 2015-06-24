/**
 * Reference: http://quickml.org/
 * 		stats-util: http://maven.twttr.com/com/twitter/common/stats-util/0.0.15/stats-util-0.0.15.pom
 */
package edu.xautjzd.activityrecognition.decisiontree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import quickml.data.AttributesMap;
import quickml.data.InstanceWithAttributesMap;
import quickml.supervised.classifier.randomForest.RandomForest;
import quickml.supervised.classifier.randomForest.RandomForestBuilder;

public class DecisionTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecisionTree dt = new DecisionTree();	
		dt.train();
		dt.predict("/decisiontree_model.txt");
		
	}
	
	/**
	 * 訓練方法
	 */
	public void train() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// 待填充的訓練集
		List<InstanceWithAttributesMap> trainingSet = new ArrayList<InstanceWithAttributesMap>();
		
		// HQL中只有類、對象和屬性的概念，沒有表和字段的概念，Attribute為類名
		Query query = session.createQuery("from Attribute");
		Iterator<Attribute> it = query.setMaxResults(512).iterate();  // 獲取前512條記錄
		while (it.hasNext()) {
			Attribute a = it.next();
			AttributesMap map = new AttributesMap();
			map.put("X_Average", a.getX_Average());
			map.put("Y_Average", a.getY_Average());
			map.put("Z_Average", a.getZ_Average());
			
			map.put("X_Deviation", a.getX_Deviation());
			map.put("Y_Deviation", a.getY_Deviation());
			
			map.put("Z_Deviation", a.getZ_Deviation());
			map.put("XY_Correlation", a.getXY_Correlation());
			map.put("YZ_Correlation", a.getYZ_Correlation());
			
			map.put("XZ_Correlation", a.getXZ_Correlation());
			map.put("X_Skewness", a.getX_Skewness());
			map.put("Y_Skewness", a.getY_Skewness());
			
			map.put("Z_Skewness", a.getZ_Skewness());
			map.put("X_Kurtosis", a.getX_Kurtosis());
			map.put("Y_Kurtosis", a.getY_Kurtosis());
			map.put("Z_Kurtosis", a.getZ_Kurtosis());
			
			InstanceWithAttributesMap item = new InstanceWithAttributesMap(map, a.getAction());
			trainingSet.add(item);
		}
		
		session.getTransaction().commit();
		session.close();
		
		final RandomForest randomForest = new RandomForestBuilder().buildPredictiveModel(trainingSet);
		
		
		try {
			FileOutputStream fs = new FileOutputStream("src/main/resources/decisiontree_model.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(randomForest);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 
	 * @param decisiontree_model
	 */
	public void predict(String decisiontree_model) {
		try {
        	
			FileInputStream fs = new FileInputStream(this.getClass().getResource(decisiontree_model).getFile());
			ObjectInputStream is = new ObjectInputStream(fs);
			RandomForest randomForest1 = (RandomForest)is.readObject();
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			// 獲取動作集合
			Query query = session.createQuery("select distinct Action from Attribute");
			List <String> actions = query.list();
			
			// 獲取待測數據集
			query = session.createQuery("from Attribute");
			Iterator<Attribute> it = query.setFirstResult(512).iterate(); 
			int i = 0;
			
			while (it.hasNext()) {
				Attribute a = it.next();
				AttributesMap map = new AttributesMap();
				map.put("X_Average", a.getX_Average());
				map.put("Y_Average", a.getY_Average());
				map.put("Z_Average", a.getZ_Average());
				
				map.put("X_Deviation", a.getX_Deviation());
				map.put("Y_Deviation", a.getY_Deviation());			
				map.put("Z_Deviation", a.getZ_Deviation());
				
				map.put("XY_Correlation", a.getXY_Correlation());
				map.put("YZ_Correlation", a.getYZ_Correlation());
				map.put("XZ_Correlation", a.getXZ_Correlation());
				
				map.put("X_Skewness", a.getX_Skewness());
				map.put("Y_Skewness", a.getY_Skewness());				
				map.put("Z_Skewness", a.getZ_Skewness());
				
				map.put("X_Kurtosis", a.getX_Kurtosis());
				map.put("Y_Kurtosis", a.getY_Kurtosis());
				map.put("Z_Kurtosis", a.getZ_Kurtosis());
				
				// 獲取各動作對應的概率
				HashMap<String,Double> pair=new HashMap<String, Double>();
				for (String action: actions) {
					pair.put(action, randomForest1.getProbability(map, action));
				}
				// 獲取最大概率的動作
				Map.Entry<String, Double> maxEntry = null;
				for (Map.Entry<String, Double> entry: pair.entrySet()) {
					if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				        maxEntry = entry;
				    }
				}
				System.out.println("Test " + ++i + " Preditcted:" + maxEntry.getKey() + "Actual:" + a.getAction());	
			}
			
			session.getTransaction().commit();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
