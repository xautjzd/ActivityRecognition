/**
 * Reference: http://quickml.org/
 * 		stats-util: http://maven.twttr.com/com/twitter/common/stats-util/0.0.15/stats-util-0.0.15.pom
 */
package edu.xautjzd.activityrecognition.decisiontree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import quickml.data.AttributesMap;
import quickml.data.InstanceWithAttributesMap;
import quickml.supervised.classifier.randomForest.RandomForest;
import quickml.supervised.classifier.randomForest.RandomForestBuilder;

public class DecisionTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<InstanceWithAttributesMap> trainingSets = new ArrayList<InstanceWithAttributesMap>();
		AttributesMap attributes = new AttributesMap();
		attributes.put("hunger", 8);
		attributes.put("color", "red");
		InstanceWithAttributesMap item = new InstanceWithAttributesMap(attributes,"angry");
		trainingSets.add(item);
		
		attributes.put("hunger", 6);
		attributes.put("color", "red");
		item = new InstanceWithAttributesMap(attributes,"angry");
		trainingSets.add(item);
		
		attributes.put("hunger", 7);
		attributes.put("color", "red");
		item = new InstanceWithAttributesMap(attributes,"angry");
		trainingSets.add(item);
		
		attributes.put("hunger", 7);
		attributes.put("color", "blue");
		item = new InstanceWithAttributesMap(attributes,"not angry");
		trainingSets.add(item);
		
		attributes.put("hunger", 2);
		attributes.put("color", "red");
		item = new InstanceWithAttributesMap(attributes,"not angry");
		trainingSets.add(item);
		
		attributes.put("hunger", 2);
		attributes.put("color", "blue");
		item = new InstanceWithAttributesMap(attributes,"not angry");
		
		final RandomForest randomForest = new RandomForestBuilder().buildPredictiveModel(trainingSets);
		
		
		try {
			FileOutputStream fs = new FileOutputStream("test.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(randomForest);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		 
        try {
        	FileInputStream fs = new FileInputStream("test.txt"); 
			ObjectInputStream is = new ObjectInputStream(fs);
			RandomForest randomForest1 = (RandomForest)is.readObject();
			
			AttributesMap test = new AttributesMap();
			test.put("hunger", 20);
			test.put("color", "red");
			System.out.println("Prediction: " + randomForest1.getProbability(attributes, "not angry"));
			
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
