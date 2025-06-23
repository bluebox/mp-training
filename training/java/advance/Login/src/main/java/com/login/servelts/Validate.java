package com.login.servelts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Validate extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ServletContext context=getServletContext();
			String user=context.getInitParameter("user");
			String password=context.getInitParameter("password");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/security", user, password);  
			ps=conn.prepareStatement("Select * from logins where username=? and password=?");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out= res.getWriter();
		res.setContentType("text/html");
		try {
			String username=req.getParameter("userName");
			String pass=req.getParameter("password");
			ps.setString(1, username);
			ps.setString(2, pass);
			ResultSet rs= ps.executeQuery();
			if(rs.next())
			{
			
				out.println("<br> <h1>Login sucessfull");
				HttpSession session=req.getSession();
				session.setAttribute("username", username);
				RequestDispatcher dispatcher=req.getRequestDispatcher("/home");
				dispatcher.forward(req, res);
				
			}
			else
			{
				out.println("In valid details");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
