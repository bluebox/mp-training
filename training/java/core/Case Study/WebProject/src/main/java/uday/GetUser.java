package uday;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/GetUser")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
	String url = "jdbc:mysql://localhost:3306/uday";
	String user = "devUser";
	String password = "Medplus@1234";

	public GetUser() {
		super();
	}

	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.prepareCall("select * from student");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	    
	  
	    String method = request.getMethod();
	    
	    if ("GET".equalsIgnoreCase(method)) {
	        doGet(request, response);
	    } else if ("POST".equalsIgnoreCase(method)) {
	        doPost(request, response);
	    }
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			res.setContentType("text");
			ResultSet r=stmt.executeQuery();
			PrintWriter pw = res.getWriter();
			while(r.next())
			{
				int id = r.getInt("id");
				String name = r.getString("name");
				int age = r.getInt("age");
				String dept = r.getString("department");
				int sal =r.getInt("salary");
				pw.println(id+""+name+""+age+""+dept+""+sal);
			}
			System.out.println("in service");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
