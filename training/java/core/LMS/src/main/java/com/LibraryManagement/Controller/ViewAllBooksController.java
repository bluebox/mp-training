package com.LibraryManagement.Controller;

import java.io.IOException;
import java.util.List;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewallBooks")
public class ViewAllBooksController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BookServiceImplementation bookService = new BookServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//        	//System.out.println("gopi");
//            List<Book> books = bookService.getAllBooks();
//            request.setAttribute("bookList", books);
////            for(Book i:books)
////            {
////            	System.out.println(i.getAuthor());
////            }
//            request.getRequestDispatcher("ViewAllBooks.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(500, "Internal server error");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String bookIdParam = request.getParameter("bookId");

        if (bookIdParam == null || action == null) {
            response.sendRedirect(request.getContextPath() + "/viewBooks");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdParam);
            Book book = bookService.getBookById(bookId);

            if (book == null) {
                response.sendRedirect(request.getContextPath() + "/viewBooks");
                return;
            }

            switch (action) {
                case "update":
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/WEB-INF/views/updateBookDetails.jsp").forward(request, response);
                    break;

                case "saveUpdate":
                    String title = request.getParameter("title");
                    String author = request.getParameter("author");
                    String category = request.getParameter("category");
                    String statusParam = request.getParameter("status");

                    char status = (statusParam != null && !statusParam.isEmpty()) ? statusParam.charAt(0) : 'a';

                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setCategory(category);
                    book.setStatus(status);

                    bookService.updateBook(book);
                    response.sendRedirect(request.getContextPath() + "/viewBooks");
                    break;

                case "toggleAvailability":
                    char currentAvailability = book.getAvailability();
                    char newAvailability = (currentAvailability == 'a' || currentAvailability == 'A') ? 'i' : 'a';
                    book.setAvailability(newAvailability);
                    bookService.updateBook(book);
                    response.sendRedirect(request.getContextPath() + "/viewBooks");
                    break;

                default:
                	 request.setAttribute("book", book);
                    response.sendRedirect(request.getContextPath() + "/viewBooks");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/viewBooks");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Internal server error");
        }
    }
}
