package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import Service.ServiceLayer;
import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;

/**
 * Servlet implementation class BooksviewController
 */
@WebServlet("/viewbooks")
public class BooksviewController extends HttpServlet {
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
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceLayer bookservice=new ServiceLayer();
		List<Book> list=null;
		try {
			list = bookservice.getBooks();
			request.setAttribute("list", list);
			request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
		}
	}
	


}
