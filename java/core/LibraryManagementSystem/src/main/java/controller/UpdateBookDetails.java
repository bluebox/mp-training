package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.Book;
import Service.LibraryService;

/**
 * Servlet implementation class UpdateBookDetails
 */
@WebServlet("/updateBook")
public class UpdateBookDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int bookId =Integer.parseInt(request.getParameter("bookId"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String category = request.getParameter("category");
			String status = request.getParameter("status");
			String availability = request.getParameter("availability");

			Book book=new Book();
			book.setBookId(bookId);
            book.setAuthor(author);
            book.setAvailability(availability.toUpperCase().charAt(0));
            book.setCategory(category);
            book.setStatus(status.toUpperCase().charAt(0));
            book.setTitle(title);
            LibraryService lib;
            PrintWriter out = response.getWriter();
			try {
				lib = new LibraryService();
				lib.updateBook(book);
				out.println("<html><body><b>Book Details Successfully Updated</b></body></html>");
			} catch (Exception e) {
				out.println("<html><body><b>Book Update Failed</b></body></html>");
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
