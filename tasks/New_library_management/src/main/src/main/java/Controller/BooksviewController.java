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

import Service.BookService;
import domain.Book;

/**
 * Servlet implementation class BooksviewController
 */
@WebServlet("/viewbooks")
public class BooksviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookService bookservice=new BookService();
		List<Book>list=bookservice.viewallbooks();
		System.out.println(list);
		if(list.size()>0) {
		 request.setAttribute("list", list);
		 System.out.println("from controller"+list);
		 RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/bookview.jsp");
 		rd.forward(request, response);
		}
		PrintWriter out=response.getWriter();
		out.println("<h1>Books are empty</h1>");
		 
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
