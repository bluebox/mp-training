
package dev.saketh.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@WebServlet("/readServlet")
public class ReadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public void init(ServletConfig config) {
		try {


			System.out.println("init()");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("doPost()");
		String username=request.getParameter("username");
		

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name = '" + username + "'");
			if(resultSet.next()) {
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h1>User Details</h1>");
				out.println("<p>Username: " + username + "</p>");
				out.println("<p>Email: " + email + "</p>");
				out.println("<p>Password: " + password + "</p>");
				out.println("</body></html>");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<h1>No user found with username: " + username + "</h1>");
				out.println("</body></html>");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void destroy() {
		try {
			System.out.println("destroy()");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
