/**
 * @author yarden
 */
package com;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager {

	private static ExtentReports extent = null;
	private static String EXTENT_REPORTS_OUTPUT_DIR = "./test-output/extent-reports/";
	final static String FILE_NAME = "ExtentAutomationReport.html";
    static String filePath;
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null)
        	createInstance();
        return extent;
    }
    
    public static synchronized void endExtentReportsForSuite(){
    	extent.flush();
    	extent = null;
    }
    
    public static String getExtentReportFileName(){
    	return FILE_NAME;
    }
    
    public static String getExtentReportOutputDir(){
    	return EXTENT_REPORTS_OUTPUT_DIR;
    }
    
    public static void changeExtentReportOutputDir(String newDir){
    	EXTENT_REPORTS_OUTPUT_DIR = newDir;
    }
    
    private static void createInstance() {
    	filePath = EXTENT_REPORTS_OUTPUT_DIR + FILE_NAME;
    	System.out.println("Creating a new instance of Extent Reports: " + filePath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(false);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(FILE_NAME);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(FILE_NAME);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        if (!FileHelper.isFileExists(filePath)) {
        	FileHelper.createDirectory(EXTENT_REPORTS_OUTPUT_DIR);
			FileHelper.appendAStringToEndOfExistingFile(filePath, "");
		}
    }
	
}
