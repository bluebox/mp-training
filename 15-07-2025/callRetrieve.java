package com.prana;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class callRetrieve {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medplus","root","pranamya@2004");
        String in = "select * from stu";
        CallableStatement cs = con.prepareCall(in);
        System.out.println(cs.execute());
	}
}
