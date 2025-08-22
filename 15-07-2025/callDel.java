package com.prana;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class callDel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medplus","root","pranamya@2004");
        String in = "delete from stu WHERE id = ?";
        CallableStatement cs = con.prepareCall(in);
        cs.setInt(1, 3);
       // cs.setString(2, "sis");
        cs.execute();
	}

}
