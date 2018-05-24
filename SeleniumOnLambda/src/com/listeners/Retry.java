package com.listeners;

import java.util.HashSet;
import java.util.Set;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		if (isRetryTestsReachMaximum() || isAfterSecondRetry(result))
			return false;
		markTestAsRetry(result);
		PrintRetryTestInLog(result);
		return true;
	}
	
	private static Set<String> testsAfterFirstRetry = new HashSet<>();
	private static Set<String> testsAfterSecondRetry = new HashSet<>();
	private static final int MAX_TESTS_TO_RETRY = 100;
	
	public static boolean isTestRetried(ITestContext testContext) {
		return testsAfterFirstRetry.contains(testContext.getName());
	}
	
	private boolean isRetryTestsReachMaximum(){
		return testsAfterFirstRetry.size() > MAX_TESTS_TO_RETRY;
	}
	
	private void markTestAsRetry(ITestResult result){
		if (isAfterFirstRetry(result))
			markTestAsSecondRetry(result);
		else
			markTestAsFirstRetry(result);
	}

	private boolean isAfterFirstRetry(ITestResult result) {
		return testsAfterFirstRetry.contains(getTestName(result));
	}
	
	private boolean isAfterSecondRetry(ITestResult result) {
		return testsAfterSecondRetry.contains(getTestName(result));
	}

	private void markTestAsFirstRetry(ITestResult result) {
		testsAfterFirstRetry.add(getTestName(result));
	}
	
	private void markTestAsSecondRetry(ITestResult result) {
		testsAfterSecondRetry.add(getTestName(result));
	}
	
	private void PrintRetryTestInLog(ITestResult result){
		System.out.println("Test " + getTestName(result) + " is retried");
	}
	
	private String getTestName(ITestResult result){
		return result.getTestContext().getName();
	}
}