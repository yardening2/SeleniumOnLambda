package com.listeners;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

public class TestNGThreadCountHolder implements IAlterSuiteListener{
	
	private static int firstXmlThreadCount;

	@Override
	public void alter(List<XmlSuite> suites) {
		firstXmlThreadCount = suites.get(0).getThreadCount();
		System.out.println("Thread count is " + firstXmlThreadCount);
	}
	
	public static int getFirstXmlThreadCount(){
		return firstXmlThreadCount;
	}

}
