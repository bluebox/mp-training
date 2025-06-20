package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DataManagement")
public class DataManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name;
	int age;
	String email;
	String phno;
	String branch;
	String language;
	String state;
	String city;
	Connection conn;
    public DataManagement() {
        super();
    }
    
    public void init() {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		name=request.getParameter("name");
		age= Integer.parseInt(request.getParameter("age"));
		email=request.getParameter("email");
		phno=request.getParameter("phno");
		branch=request.getParameter("branch");
		language = String.join(",",request.getParameterValues("lang"));
		state=request.getParameter("state");
		city=request.getParameter("city"); 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("text/html");
		try {
			PreparedStatement ps=conn.prepareStatement("insert into form values(?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, email);
			ps.setString(4, phno);
			ps.setString(5, branch);
			ps.setString(6, language);
			ps.setString(7, state);
			ps.setString(8, city);
			PrintWriter out=response.getWriter();
			if(!ps.execute()) {
				response.sendRedirect("http://localhost:8080/Registration_Form_using_JDBC/Show") ;
//				("<a href='http://localhost:8080/Registration_Form_using_JDBC/Show'><Button>Select</Button></a>");
			}
			else {
				out.println("<h1>Name : "+name+"</h1>");
				out.println("<h1>Age : "+age+"</h1>");
				out.println("<h1>Email : "+email+"</h1>");
				out.println("<h1>Phno : "+phno+"</h1>");
				out.println("<h1>branch : "+branch+"</h1>");
				out.println("<h1>language : "+language+"</h1>");
				out.println("<h1>state : "+state+"</h1>");
				out.println("<h1>city : "+city+"</h1>");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
