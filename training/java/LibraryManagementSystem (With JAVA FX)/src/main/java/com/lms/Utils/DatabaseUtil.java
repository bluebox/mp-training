package com.lms.Utils;

public class DatabaseUtil {
	private static String url;
	private static String user;
	
	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}

	private static String password;
	
	static {
		url = "jdbc:mysql://localhost:3306/library";
		user = "root";
	   	password = "root@pokemon";
	}
}
