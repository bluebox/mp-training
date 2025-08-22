package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.BookServiceImplementation;
import com.library.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/BookController")
public class BookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookService bookService;
    @Override
    public void init() throws ServletException {
        BookDAO bookDAO = new BookDAOImplementation();
        this.bookService = new BookServiceImplementation(bookDAO);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("addbook".equalsIgnoreCase(action)) {
            handleAddBook(request, response);
        } else if ("viewbook".equalsIgnoreCase(action)) {
            handleViewBooks(request, response);
        } else if ("updateBook".equalsIgnoreCase(action)) {
            try {
				handleUpdateBook(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("updateAvailability".equalsIgnoreCase(action)) {
            try {
				handleUpdateAvailability(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("saveUpdate".equalsIgnoreCase(action)) {
            handleSaveUpdate(request, response);
        } else if ("saveAvailabilityUpdate".equalsIgnoreCase(action)) {
            try {
				handleSaveAvailabilityUpdate(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleViewBooks(request, response);
    }

    private void handleAddBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        String titleErrorLabel = "";
        String authorErrorLabel = "";
        String categoryErrorLabel = "";

        boolean isValid = true;

        if (title == null || title.trim().isEmpty()) {
            titleErrorLabel = "Title is required *";
            isValid = false;
        }

        if (author == null || author.trim().isEmpty()) {
            authorErrorLabel = "Author is required *";
            isValid = false;
        }

        if (category == null || category.trim().isEmpty()) {
            categoryErrorLabel = "Category is required *";
            isValid = false;
        }

        if (!isValid) {
            request.setAttribute("titleErrorLabel", titleErrorLabel);
            request.setAttribute("authorErrorLabel", authorErrorLabel);
            request.setAttribute("categoryErrorLabel", categoryErrorLabel);

            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("category", category);

            request.getRequestDispatcher("/AddBook.jsp").forward(request, response);
            return;
        }

        int bookId = bookService.addBook(new Book(title.trim(), author.trim(), category.trim()));

        sendAlertAndRedirect(response, "Successfully added Book with ID: " + bookId, "Book.html");
    }

    private void handleViewBooks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/ViewBooks.jsp").forward(request, response);
    }

    private void handleUpdateBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bookIdParam = request.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID missing for update.");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book ID format.");
            return;
        }

        Book book = bookService.getBookById(bookId);

        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found for ID: " + bookId);
            return;
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/UpdateBook.jsp").forward(request, response);
    }

    private void handleUpdateAvailability(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String bookIdParam = request.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book ID missing for availability update.");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Book ID format.");
            return;
        }

        Book book = bookService.getBookById(bookId);

        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found for ID: " + bookId);
            return;
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/UpdateBookAvailability.jsp").forward(request, response);
    }

    private void handleSaveUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String availability = request.getParameter("availability");

        Book updatedBook = new Book(bookId, title, author, category, status, availability);
        bookService.updateBook(updatedBook);

        sendAlertAndRedirect(response, "Book updated successfully!", "BookController?action=viewbook");
    }

    private void handleSaveAvailabilityUpdate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String availability = request.getParameter("availability");

        Book book = bookService.getBookById(bookId);
        if (book == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found for ID: " + bookId);
            return;
        }

        book.setAvailability(availability);
        bookService.updateAvailability(book);

        sendAlertAndRedirect(response, "Availability updated successfully!", "BookController?action=viewbook");
    }

    private void sendAlertAndRedirect(HttpServletResponse response, String message, String redirectUrl)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");
        out.println("window.location = '" + redirectUrl + "';");
        out.println("</script>");
    }
}
