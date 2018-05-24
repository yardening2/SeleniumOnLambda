/**
 * @author yarden
 */
package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class FileHelper {

	/**
	 * Appends the given String to the end of a file. Every string will be appended in a new line.
	 * <p>
	 * If the file doesn't exist, this method will create the file in the given path
	 * 
	 * @param filePath The path to the file in the file system including the file name and extension
	 * @param stringToWrite The string to write in the file
	 */
	public static void appendAStringToEndOfExistingFile(String filePath, String stringToWrite){
		System.out.println("Appending: " + stringToWrite + " to file: " + filePath);
		try (FileWriter fw = new FileWriter(filePath, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(stringToWrite);
		} catch (IOException e) {
			System.out.println("Failed to write to " + filePath);
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Creates a property file with the given property name and value.
	 * <p>
	 * If the file already exists, this method will override the file in the given path
	 * 
	 * @param filePath The path to the file in the file system including the file name and extension
	 * @param propertyName The name of the property
	 * @param propertyValue The value that will be stored in the property
	 */
	public static void createAPropertyFile(String filePath, String propertyName, String propertyValue){
		System.out.println("Creating a property file: " + filePath + " with property: " + propertyName + "=" + propertyValue);
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(filePath);
			prop.setProperty(propertyName, propertyValue);
			prop.store(output, null);
		} catch (IOException e) {
			System.out.println("Failed to write to " + filePath);
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Returns the value of the property 'propertyName' from the file 'filePath'
	 * 
	 * @param filePath The path to the file in the file system including the file name and extension
	 * @param propertyName The name of the property
	 */
	public static String getPropertyFromFile(String filePath, String propertyName){
		Properties prop = new Properties();
		InputStream input = null;
		String result = null;
		try {

			input = new FileInputStream(filePath);
			prop.load(input);
			result = prop.getProperty(propertyName);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("The property: " + propertyName + " from file: " + filePath + " value is: " + result);
		return result;
	}

	public static boolean isFileExists(String filePath){
		return new File(filePath).exists();
	}

	public static void createDirectory(String directoryPath){
		System.out.println("Creating directory " + directoryPath);
		new File(directoryPath).mkdir();
	}

}
