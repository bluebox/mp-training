package com.library.service;

public class SQLQuery {
	protected String insertMember="INSERT INTO member(name, email, mobile, gender, address) VALUES(?,?,?,?,?)";
	protected String selectAllBooks="SELECT * FROM book";
}
