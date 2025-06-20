package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 2L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			Statement s2=conn.createStatement();
			if(s2.executeUpdate("delete from form where email="+req.getParameter("email"))>0) {
				res.sendRedirect("http://localhost:8080/Registration_Form_using_JDBC/Show");
			}
			else {
				out.print("Not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
