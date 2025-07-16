package com.prana;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class callIn {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
	       Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medplus","root","pranamya@2004");
           String in = "insert into stu (id,name) values(?,?)";
           CallableStatement cs = con.prepareCall(in);
           cs.setInt(1, 4);
           cs.setString(2, "sis");
           cs.execute();
           
	}

}
