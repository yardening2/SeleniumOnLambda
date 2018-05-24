package com;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.listeners.TestWithAWSLambdaListener;

public class Main {

	public static void main(String[] args) {
		String testQualifiedName;
		try {
			testQualifiedName = args[0];
		} catch (Exception e1) {
			System.out.println("Failed to get the first jar arg[0]. should be the test to run");
			throw e1;
		}
		String testServerURL;
		try {
			testServerURL = args[1];
		} catch (Exception e1) {
			System.out.println("Failed to get the second jar arg[1]. should be the test BaseUrl");
			throw e1;
		}
		System.out.println("Executing test: " + testQualifiedName + " with TestNG on test server url:" + testServerURL);
		BaseURL.setBaseUrl(testServerURL);
		TestNG testng = new TestNG();
		try {
			testng.setTestClasses(new Class[] { Class.forName(testQualifiedName)});
			testng.addListener((ITestNGListener) new TestListenerAdapter());
			testng.addListener((ITestNGListener) new TestWithAWSLambdaListener());
			System.out.println("Finished importing TestNG listeners");
			testng.run();
			System.out.println("The test finished");
			
			String isTestPassed = FileHelper.getPropertyFromFile(TestWithAWSLambdaListener.getTestResultPropFilePath(), TestWithAWSLambdaListener.TEST_RESULT_PROPERTY);
			if (!isTestPassed.equals("true"))
				System.exit(1);
			else
				System.out.println("The test " + testQualifiedName + " passed");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: class not found: " + testQualifiedName);
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Failed executing test: " + testQualifiedName);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
