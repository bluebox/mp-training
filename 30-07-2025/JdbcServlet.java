package servletSamples;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/JdbcServlet")
public class JdbcServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try {
			String query="Insert into UserDetails(firstName,lastName,email,password) values('karuna','Setty','sk@gmail.com','root')";
			Statement stmt=conn.createStatement();
			  
			int rs=stmt.executeUpdate(query);
			if(rs>0) {
				
				PrintWriter out=response.getWriter();
				out.println("Insertion successful");
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}


}
