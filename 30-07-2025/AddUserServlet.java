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

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("Text/html");
		
		PrintWriter out=resp.getWriter();
		try {
			String query="Insert into UserDetails(firstName,lastName,email,password) values(?,?,?,?)";
			PreparedStatement stmt=conn.prepareStatement(query);
			  
			stmt.setString(1,req.getParameter("firstName"));
			stmt.setString(2,req.getParameter("lastName"));
			stmt.setString(3,req.getParameter("email"));
			stmt.setString(4,req.getParameter("password"));
			
			int rs1=stmt.executeUpdate();
			if(rs1>0) {
				
				out.println("user Added successful");
				
			}
			else {
				out.println("user can't be added");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	}

	
	

}
