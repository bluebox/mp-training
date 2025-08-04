package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Domain.Member;
import Service.ServiceLayer;

/**
 * Servlet implementation class ViewAllMembersController
 */
@WebServlet("/ViewAllMembersController")
public class ViewAllMembersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String url="jdbc:mysql://127.0.0.1:3306/library_management_system";
	private final String username="root";
	private final String password="root";
	
	public Connection connectionestablish() {
		Connection c=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try{
			 c = DriverManager.getConnection(url, username, password);
			c.setAutoCommit(false);
			System.out.println("connection established with db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	private ServiceLayer Service = new ServiceLayer();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Member> members = Service.getAllMembers();
			System.out.println(members.size());
			request.setAttribute("membersList", members);
			request.getRequestDispatcher("viewAllMembers.jsp").forward(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to load members.");
			request.getRequestDispatcher("viewAllMembers.jsp").forward(request, response);
		}
	}



}
