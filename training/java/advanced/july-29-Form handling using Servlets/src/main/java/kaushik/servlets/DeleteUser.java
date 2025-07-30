package kaushik.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.println("<html> ");
		pw.println("<form method='POST'>");
		pw.println("Email: <input type='email' name='email'/><br>");
		pw.println("<input type='submit' value='Delete'/>");
		pw.println("</form>");
		pw.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    PrintWriter pw = response.getWriter();
	    String email = request.getParameter("email");
	    
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "kaushik");
	        String query = "DELETE FROM users WHERE email like ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, email);
	        
	        int rowsDeleted = pstmt.executeUpdate();
	        if (rowsDeleted > 0) {
	            pw.println("User deleted successfully.");
	        } else {
	            pw.println("No user found with the given email.");
	        }
	        
	        pstmt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        pw.println("Error: " + e.getMessage());
	    }
	}


}
