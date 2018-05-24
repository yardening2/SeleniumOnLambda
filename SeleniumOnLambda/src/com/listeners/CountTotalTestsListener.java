package com.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class CountTotalTestsListener implements ISuiteListener{

	private static int totalAmountOfTestsInSuite;

	@Override
	public void onStart(ISuite suite) {
		totalAmountOfTestsInSuite = suite.getXmlSuite().getTests().size();
	}
	
	public static int getTotalAmountOfTestsInSuite(){
		return totalAmountOfTestsInSuite;
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
