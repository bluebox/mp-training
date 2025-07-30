package kaushik.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/ReadData")
public class ReadData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ReadData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","kaushik");
			String query="Select * from users";
			Statement stmt=conn.createStatement();	
			ResultSet rs = stmt.executeQuery(query);
			out.write("<html> <body>");
			out.write(String.format("%10s | %10s <br>","email","password"));
			while(rs.next()) {
				out.write(String.format("%10s | %10s <br>",rs.getString("email"),rs.getString("password")));
			}
			out.write("</body> </html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
