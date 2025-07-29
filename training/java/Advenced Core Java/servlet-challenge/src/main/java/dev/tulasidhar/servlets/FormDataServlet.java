package dev.tulasidhar.servlets;

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



@WebServlet("/FormDataServlet")
public class FormDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public FormDataServlet() {
		System.out.println("This always executes");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		pw.println("<html> ");
		pw.println("<form method='POST' action='FormDataServlet'>");
		pw.println("Email: <input type='email' name='email'/><br>");
        pw.println("Password: <input type='password' name='password'/><br>");
        pw.println("<input type='submit' value='Submit'/>");
		pw.println("</form>");
		pw.println("</html> ");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets","root","root@pokemon");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES(?,?)");
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			int result = stmt.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			
			if(result > 0) {
				pw.print("<h1>Successfully added the member</h1>");
			}
			else {
				pw.print("<h1>Cannot add the member</h1>");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
