package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Show")
public class Show extends GenericServlet {
	private static final long serialVersionUID = 2L;
	public Connection conn;
	ResultSet rs = null;
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","Practice","Vbhanu@2003");
			Statement s=conn.createStatement();
			rs=s.executeQuery("select * from form");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out=res.getWriter();
		out.println("""
				<table style="border:3px solid">
					<tr>
						<th>
							Name
						</th>
						<th>
							Age
						</th>
						<th>
							Email
						</th>
						<th>
							Phone no
						</th>
						<th>
							Branch
						</th>
						<th>
							Language
						</th>
						<th>
							State
						</th>
						<th>
							City
						</th>
					<tr>
					""");
		try {
			while(rs.next()) {
				out.println("""
							<tr>
								<td>
							"""
						+rs.getString(1)+
							"""
								</td>
								<td>
							"""
						+rs.getInt(2)+
							"""
								</td>
								<td>
							"""
						+rs.getString(3)+
							"""
								</td>
								<td>
						"""
						+rs.getString(4)+
						"""
								</td>
								<td>
						"""
						+rs.getString(5)+
						"""
								</td>
								<td>
									"""
						+rs.getString(6)+
						"""
								</td>
								<td>
							"""
						+rs.getString(7)+
						"""
								</td>
								<td>
							"""
						+rs.getString(8)+
						"""
								</td>
								<td>
								<a href='http://localhost:8080/Demo1/Update?phno='+rs.getString(4)
								><Button>Update</Button></a>
								</td>
								<td>
								<a href='http://localhost:8080/Demo1/Delete?phno="+rs.getString(4)
								><Button>Delete</Button></a>
								</td>
							<tr>
							""");
			}
			out.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
