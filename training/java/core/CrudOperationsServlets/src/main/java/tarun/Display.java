package tarun;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/Display")
public class Display extends HttpServlet {
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			String query="select * from user";
			Connection con=DButil.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			while(rs.next())
			{
			  int id=rs.getInt("user_id");
			  String name=rs.getString("user_name");
			  int age=rs.getInt("user_age");
			  pw.println("<h1>"+id+" "+name+" "+age+"<h1>\n");
			  
			}
			rs.close();
			
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
	}

	

