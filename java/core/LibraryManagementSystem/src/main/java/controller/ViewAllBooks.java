package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.Book;
import Service.LibraryService;

/**
 * Servlet implementation class ViewAllBooks
 */
@WebServlet("/viewAllBooks")
public class ViewAllBooks extends HttpServlet {
	private static final long serialVebookionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LibraryService lib;
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		try {
			lib = new LibraryService();
			List<Book> books = lib.viewAllBooks();
			out.println("<h1>All Books</h1>");
			for(Book book:books) {
				out.println("<h3>Book ID : "+book.getBookId()+"</h3>");
				out.println("<h3>Title : "+book.getTitle()+"</h3>");
				out.println("<h3>Author : "+book.getAuthor()+"</h3>");
				out.println("<h3>Category : "+book.getCategory()+"</h3>");
				out.println("<h3>Status : "+book.getStatus()+"</h3>");
				out.println("<h3>Availability : "+book.getAvailability()+"</h3>");
				out.println("-".repeat(40));
			}
		} catch (Exception e) {
			out.println("<html><body><b>No Books there..</b></body></html>");
			e.printStackTrace();
		}
	}

	

}
