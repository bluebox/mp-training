package kaushik.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUser() {
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
		pw.println("Password: <input type='password' name='password'/><br>");
		pw.println("<input type='submit' value='Submit'/>");
		pw.println("</form>");
		pw.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "kaushik");
			String query = "insert into users values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				out.print("<h1>Successfully added the member</h1>");
			} else {
				out.print("<h1>Cannot add the member</h1>");
			}
		} catch (SQLException e) {
			out.write(e.getMessage());
		}
	}

}
