package com.listeners;

import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;

import com.ExtentReportsManager;
import com.FileHelper;

public class TestWithAWSLambdaListener implements ISuiteListener{
	
	private static String testResultPropFilePath = null;
	public static final String TEST_RESULT_PROPERTY = "is.test.passed";

	@Override
	public void onStart(ISuite suite) {
		System.out.println("AWS lambda test listener was invoked");
		ExtentReportsManager.changeExtentReportOutputDir("/tmp/output/");
	}

	@Override
	public void onFinish(ISuite suite) {
		testResultPropFilePath = ExtentReportsManager.getExtentReportOutputDir() + "testResultsProp.properties";
		String isTestPassedProperty = getIsTestPassedProperty(suite);
		FileHelper.createAPropertyFile(testResultPropFilePath, TEST_RESULT_PROPERTY, isTestPassedProperty);
	}
	
	private String getIsTestPassedProperty(ISuite suite){
		String isTestPassed = "true";
		if (countFailedTests(suite) > 0)
			isTestPassed = "false";
		return isTestPassed;
	}
	
	public static String getTestResultPropFilePath(){
		return testResultPropFilePath;
	}
	
	private int countFailedTests(ISuite suite){
		Map<String, ISuiteResult> suiteResults = suite.getResults();
		int amountOfFailedTests = 0;
		for (ISuiteResult result : suiteResults.values()){
			amountOfFailedTests += result.getTestContext().getFailedTests().getAllResults().size();
		}
		return amountOfFailedTests;
	}

}
