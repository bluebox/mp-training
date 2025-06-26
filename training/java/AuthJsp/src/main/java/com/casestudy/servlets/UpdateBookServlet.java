package com.casestudy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casestudy.dao.BooksDao;
import com.casestudy.domain.Book;
import com.casestudy.domain.Status;


@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private BooksDao booksDao;

    @Override
    public void init() {
        booksDao = new BooksDao();
    }

    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("Fetch Book".equals(action)) {
	            try {
	                int bookId = Integer.parseInt(request.getParameter("bookId"));
	                Book book = booksDao.searchBook(bookId);
	                if (book != null) {
	                    request.setAttribute("book", book);
	                } else {
	                    request.setAttribute("message", "Book not found.");
	                }
	            } catch (NumberFormatException e) {
	                request.setAttribute("message", "Invalid Book ID.");
	            }
	        } else if ("Update Book".equals(action)) {
	            try {
	                int bookId = Integer.parseInt(request.getParameter("bookId"));
	                String title = request.getParameter("title").trim();
	                String author = request.getParameter("author").trim();
	                String category = request.getParameter("category").trim();
	                String statusCode = request.getParameter("status");

	                Book book = new Book(bookId, title, author, category, Status.fromCode(statusCode), null);

	                boolean updated = booksDao.updateBook(book);
	                if (updated) {
	                    request.setAttribute("message", "Book updated successfully.");
	                } else {
	                    request.setAttribute("message", "Book update failed.");
	                }

	            } catch (Exception e) {
	                request.setAttribute("message", "Error updating book.");
	            }
	        }

	        request.getRequestDispatcher("update_book.jsp").forward(request, response);
	    }
	

}
