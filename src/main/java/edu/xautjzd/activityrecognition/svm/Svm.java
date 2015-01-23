/**
 * 参考： 
 * 	1. http://blog.csdn.net/yangliuy/article/details/8041343
 * 	2. https://github.com/cjlin1/libsvm
 */

package edu.xautjzd.activityrecognition.svm;

import java.io.IOException;

public class Svm {
	public static void main(String[] args) throws IOException {
		/**
			Test for svm_train and svm_predict
			svm_train:
				param: String[], parse result of command line parameter of svm-train
				return: String, the directory of modelFile
			svm_predect:
				param: String[], parse result of command line parameter of
					svm-predict, including the modelfile
				return: Double, the accuracy of SVM classification
		 */
		String[] trainArgs = { "svm_train_set" }; // directory of training file
		String modelFile = svm_train.main(trainArgs);
		
		/*PrintWriter writer = new PrintWriter(new File("src/main/resources","marks.txt"));
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		
		// getResourceAsStream will resolve /hello to /target/classes/hello
		DataInputStream in = new DataInputStream(Svm.class.getResourceAsStream("/hello"));
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		System.out.println(input.readLine());*/
		
	//	String modelFile = "svm_train_set.model";
		String[] testArgs = { "svm_test_set", modelFile,
				"svm_result" }; // directory of test file, model file, result file
		Double accuracy = svm_predict.main(testArgs);
		System.out.println("SVM Classification is done! The accuracy is "
				+ accuracy);

		// Test for cross validation
		// String[] crossValidationTrainArgs = {"-v", "10",
		// "UCI-breast-cancer-tra"};// 10 fold cross validation
		// modelFile = svm_train.main(crossValidationTrainArgs);
		// System.out.print("Cross validation is done! The modelFile is " +
		// modelFile);
	}
}
