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
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='styles.css'");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>All Books</h1>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th> BookId </th>");
			out.println("<th> Title </th>");
			out.println("<th> Category </th>");
			out.println("<th> Author </th>");
			out.println("<th> Status </th>");
			out.println("<th> Availability </th>");
			out.println("</tr>");
			for(Book book:books) {
				out.println("<tr>");
				out.println("<td>"+book.getBookId()+ "</td>");
				out.println("<td>"+book.getTitle()+ "</td>");
				out.println("<td>"+book.getCategory()+ "</td>");
				out.println("<td>"+book.getAuthor()+ "</td>");
				out.println("<td>"+book.getStatus()+ "</td>");
				out.println("<td>"+book.getAvailability()+ "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			out.println("<html><body><b>No Books there..</b></body></html>");
			e.printStackTrace();
		}
	}

	

}
