package com.casestudy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
/*	CREATE TABLE Member (
		    memberId INT PRIMARY KEY AUTO_INCREMENT,
		    name VARCHAR(255) NOT NULL,
		    email VARCHAR(255) NOT NULL UNIQUE,
		    mobile BIGINT NOT NULL UNIQUE,
		    gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),
		    address VARCHAR(255) NOT NULL
		);
		CREATE TABLE member_log (
		    memberId INT PRIMARY KEY AUTO_INCREMENT,
		    name VARCHAR(255) NOT NULL,
		    email VARCHAR(255) NOT NULL UNIQUE,
		    mobile BIGINT NOT NULL UNIQUE,
		    gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),
		    address VARCHAR(255) NOT NULL
		);
		CREATE TABLE Books (
		    bookId INT PRIMARY KEY AUTO_INCREMENT,
		    title VARCHAR(255) NOT NULL,
		    author VARCHAR(255) NOT NULL,
		    category VARCHAR(100) NOT NULL,
		    status CHAR(1) NOT NULL CHECK (Status IN ('A', 'I')),
		    availability CHAR(1) NOT NULL CHECK (Availability IN ('A', 'I'))
		);

		CREATE TABLE LogBooks (
		    bookId INT,
		    title VARCHAR(255) NOT NULL,
		    author VARCHAR(255) NOT NULL,
		    category VARCHAR(100) NOT NULL,
		    status CHAR(1) NOT NULL CHECK (Status IN ('A', 'I')),
		    availability CHAR(1) NOT NULL CHECK (Availability IN ('A', 'I'))
		);

		CREATE TABLE IssueRecords (
		    issueId INT PRIMARY KEY AUTO_INCREMENT,
		    bookId INT NOT NULL,
		    memberId INT NOT NULL,
		    status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
		    issueDate DATE NOT NULL,
		    returnDate DATE,
		    FOREIGN KEY (bookId) REFERENCES Books(bookId),
		    FOREIGN KEY (memberId) REFERENCES Member(memberId)
		);

		CREATE TABLE LogIssueRecords (
		    issueId INT,
		    bookId INT NOT NULL,
		    memberId INT NOT NULL,
		    status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
		    issueDate DATE NOT NULL,
		    returnDate DATE
		);*/


    private static final String URL = "jdbc:mysql://localhost:3306/mylibrary"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Medplus@321"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}

