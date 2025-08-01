package tarun;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertUser extends HttpServlet {

	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			String query="insert into user values (?,?,?)";
			Connection con=DButil.getConnection();
			PreparedStatement stmt=con.prepareStatement(query);
			int user_id=Integer.parseInt(req.getParameter("id"));
			String user_name=req.getParameter("name");
			int user_age=Integer.parseInt(req.getParameter("age"));
		
			stmt.setInt(1,user_id);
			stmt.setString(2,user_name);
			stmt.setInt(3, user_age);
			int result=stmt.executeUpdate();
			PrintWriter pw=res.getWriter();
			
			if(result>0)
				pw.println("<h1>user created<h1>");
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
