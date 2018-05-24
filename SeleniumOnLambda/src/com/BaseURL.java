package com;

public class BaseURL {
	
	private static String baseUrl = "my.test.com";

	public static String getBaseUrl() {
		return baseUrl;
	}
	
	public static void setBaseUrl(String baseUrl){
		BaseURL.baseUrl = baseUrl;
	}

}
