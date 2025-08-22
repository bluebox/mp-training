package servletSamples;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ReadUserServlet")
public class ReadUser extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("Text/html");
		
		PrintWriter out=resp.getWriter();
		try {
			
			out.println("<html><body>");
			out.println("<table border=1><tr><td>"+"first name"+"</td><td>"+"lastName"+"</td><td>"+"email"+"</td><td>"+"password"+"</td></tr>");
			String query="select * from userDetails";
			PreparedStatement stmt=conn.prepareStatement(query);
			
			ResultSet rs1=stmt.executeQuery();
			while(rs1.next()) {
				String fname=rs1.getString("firstName");
				String lname=rs1.getString("lastName");
				String email=rs1.getString("email");
				String password=rs1.getString("password");
				
				out.println("<tr><td>"+fname+"</td><td>"+lname+"</td><td>"+email+"</td><td>"+password+"</td></tr>");
			}
			out.println("</table></body></html");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	}

	
	

}
