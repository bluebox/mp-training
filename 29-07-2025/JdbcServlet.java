

import java.io.IOException;
import java.io.PrintWriter;

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
	
	private Connection conn=null;
	public void init() {
		
		try {
			conn=DriverManager.getConnection("http:mysql://localhost:3306//mydatabase", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Statement stmt=conn.createStatement();
			String query="Insert into UserDetails(firstName,lastName,email,password) values('karuna','Setty','sk@gmail.com','root')";  
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
