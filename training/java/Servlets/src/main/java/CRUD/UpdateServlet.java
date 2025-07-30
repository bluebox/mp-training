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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
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
		String mail = request.getParameter("mail");
		String password = request.getParameter("pass");
		try {
//			Statement stmt = conn.createStatement();
//			int rows = stmt.executeUpdate("UPDATE user SET password = '" + password + "' WHERE email = '" + mail + "'");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET password = ? WHERE email = ?");
			pstmt.setString(1, password);
			pstmt.setString(2, mail);
			int rows = pstmt.executeUpdate();
			PrintWriter out = response.getWriter();
			if(rows>0) {
				out.println("<h1> User updated </h1>");
			}
			else {
				out.println("<h1> Error in updating the user </h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
