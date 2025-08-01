package CRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/read")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Connection conn;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root",System.getenv("password"));
//			System.out.println("Connection done");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement stmt;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>User Details: </h1>");
		out.print("<table style='border: 1px solid black;'>");
		out.print("<tr>");
		out.print("<th style='border: 1px solid black;'>First Name </th>");
		out.print("<th style='border: 1px solid black;'>Last Name </th>");
		out.print("<th style='border: 1px solid black;'>Email </th>");
		out.print("<th style='border: 1px solid black;'>Password </th>");
		out.print("</tr>");
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			while(rs.next()) {
				//out.println(rs.getString(1) + " \t" + rs.getString(2) + " \t" + rs.getString(3) + " \t" + rs.getString(4));
				out.print("<tr>");
				out.print("<td style='border: 1px solid black;'> " + rs.getString(1) + "</td>");
				out.print("<td style='border: 1px solid black;'> " + rs.getString(2) + "</td>");
				out.print("<td style='border: 1px solid black;'> " + rs.getString(3) + "</td>");
				out.print("<td style='border: 1px solid black;'> " + rs.getString(4) + "</td>");
				out.print("</tr>");
			}
			out.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
