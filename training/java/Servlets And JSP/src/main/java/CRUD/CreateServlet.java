package CRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String frstName = request.getParameter("frstName");
		String lastName = request.getParameter("lastName");
		String mail = request.getParameter("mail");
		String password = request.getParameter("pass");
		try {
//			Statement stmt = conn.createStatement();
//			int rows = stmt.executeUpdate("INSERT INTO user VALUES ('" + frstName + "', '" + lastName + "', '" + mail + "', '" + password + "')");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?)");
			pstmt.setString(1, frstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, mail);
			pstmt.setString(4, password);
			int rows = pstmt.executeUpdate();
			PrintWriter out = response.getWriter();
			if(rows>0) {
				out.println("<h1> User created </h1>");
			}
			else {
				out.println("<h1> Error in creating the user </h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
