package com.JDBC.JDBCProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoadAndConnectJDBC {
	private Connection con;
	private String URL;
	private String USERNAME;
	private String PASSWORD;
	
	public void connect() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties props=new Properties();
		props.load(new FileInputStream("E:\\Coding\\Eclipse\\JDBCProject\\src\\main\\java\\com\\JDBC\\JDBCProject/applications.properties"));
		this.URL=props.getProperty("url");
		this.USERNAME=props.getProperty("username");
		this.PASSWORD=props.getProperty("password");
		this.con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	

}