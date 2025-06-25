package uday;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Jdbc")
public class Jdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
	String url = "jdbc:mysql://localhost:3306/uday";
	String user = "devUser";
	String password = "Medplus@1234";

	public Jdbc() {
		super();
	}

	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.prepareCall("insert into student values (?,?,?,?,?)");
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {

			res.setContentType("text");
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			String dept = req.getParameter("department");
			int sal = Integer.parseInt(req.getParameter("salary"));
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, age);
			stmt.setString(4, dept);
			stmt.setInt(5, sal);

			int r = stmt.executeUpdate();
			PrintWriter pw = res.getWriter();
			pw.println(r + "rows inserted");
			System.out.println("in service");

		} catch (Exception e) {

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
