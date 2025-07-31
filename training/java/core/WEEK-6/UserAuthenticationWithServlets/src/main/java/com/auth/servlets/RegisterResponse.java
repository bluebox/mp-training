package com.auth.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class RegisterResponse
 */
public class RegisterResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement psInsert;
	private PreparedStatement psSelect;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) {
    	ServletContext context=config.getServletContext();
    	String dbUrl=context.getInitParameter("dbUrl");
    	String dbUser=context.getInitParameter("dbUser");
    	String dbPassword=context.getInitParameter("dbPassword");
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			psInsert=conn.prepareStatement("insert into auth.users values(?,?)");
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
				writer.println("<p>User Already Exists Please Login...</p><br>");
				writer.println("<a href='login.html'>Go To Login</a>");
				return;
			}
			
			psInsert.setString(1, gmail);
			psInsert.setString(2,password);
			int count=psInsert.executeUpdate();
			if(count>0) {
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				request.setAttribute("message", "User Registered Succesfuly...");
//				writer.println("<p>User Registered Successfully...</p><br>");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				request.setAttribute("message", "User Registration Failed...");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			psInsert.close();
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
