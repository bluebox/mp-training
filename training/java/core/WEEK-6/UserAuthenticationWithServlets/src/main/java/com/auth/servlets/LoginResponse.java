package com.auth.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement psSelect;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginResponse() {
        super();
    }
    
    public void init(ServletConfig config) {
    	ServletContext context=config.getServletContext();
    	String dbUrl=context.getInitParameter("dbUrl");
    	String dbUser=context.getInitParameter("dbUser");
    	String dbPassword=context.getInitParameter("dbPassword");
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			psSelect=conn.prepareStatement("select * from auth.users where gmail=?");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gmail=request.getParameter("gmail");
		String password=request.getParameter("password");
		response.setContentType("text/html");
		
		try(PrintWriter writer=response.getWriter()) {
			psSelect.setString(1, gmail);
			ResultSet rs=psSelect.executeQuery();
			
			if(rs.next()) {
				String gmailValue=rs.getString(1);
				String passwordValue=rs.getString(2);
				if(passwordValue.equals(password)) {
					HttpSession session=request.getSession();
					session.setAttribute("gmail", gmailValue);
					RequestDispatcher rd=request.getRequestDispatcher("homeServlet");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					request.setAttribute("message", "User Login Failed...");
					rd.include(request, response);
				}
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				request.setAttribute("message", "User Login Failed...");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void destroy() {
		try {
			psSelect.close();
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
