package uday;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUser
 */
//@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
	
   
    public UpdateUser() {
        super();
        
    }
    public void init(ServletConfig config) {
    	System.out.println("Entered First init");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(config.getInitParameter("url"), config.getInitParameter("user"),config.getInitParameter("password"));
			stmt = con.prepareCall("update student set name=? where id=?");
			System.out.println(config.getInitParameter("url"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			res.setContentType("text");
			stmt.setInt(2,Integer.valueOf(req.getParameter("id")));
			stmt.setString(1, req.getParameter("name"));
			int r=stmt.executeUpdate();
			PrintWriter pw = res.getWriter();
			pw.println(r+" rows Affected");
			System.out.println("in service");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Entered First Post");
	}

}
