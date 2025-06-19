import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private PreparedStatement ps;

	@Override
	public void init() throws ServletException {
		System.out.println("Getting DB connection...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "manoj", "Manoj@123");

			ps = conn.prepareStatement("SELECT * FROM book");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("DB connection failed", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(password.equals("1234"))
		{
			out.print("<h1> Login Sucessful</h1>");
		}
		else
		{
			out.print("<h1> Invalid Password</h1>");
			return;
			
		}
		out.print("<h1>");
		out.print(name);
		out.print("</h1>");

		out.println("<h2>Hello World - Book List</h2>");

		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				// Print all 4 columns from 'book' table
				out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - "
						+ rs.getString(4) + "<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<p>Error fetching book data.</p>");
		}
	}

	@Override
	public void destroy() {
		System.out.println("Closing DB resources...");
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
