package library.controllers;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/addBook") 
public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookService = new BookServiceImpl(); 
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    private final String CURRENT_USER = "ADMIN"; 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        populateDropdownData(request);

        request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	request.removeAttribute("message");
        request.removeAttribute("messageType");

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryDisplayName = request.getParameter("category");
        String statusSelectedString = request.getParameter("status");
        String availabilitySelectedString = request.getParameter("availability");

        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("selectedCategory", categoryDisplayName);
        request.setAttribute("selectedStatus", statusSelectedString);
        request.setAttribute("selectedAvailability", availabilitySelectedString);

        if (title == null || title.isEmpty() ||
            author == null || author.isEmpty() ||
            categoryDisplayName == null || categoryDisplayName.isEmpty() ||
            statusSelectedString == null || statusSelectedString.isEmpty() ||
            availabilitySelectedString == null || availabilitySelectedString.isEmpty()) {

            request.setAttribute("message", "Please fill in all fields.");
            request.setAttribute("messageType", "error");
            populateDropdownData(request); 
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
            return;
        }

        try {
            BookCategory bookCategory = BookCategory.fromDisplayName(categoryDisplayName);
            BookStatus bookStatus = BookStatus.valueOf(statusSelectedString);
            BookAvailability bookAvailability = BookAvailability.valueOf(availabilitySelectedString);

            Book newBook = new Book(title, author, bookCategory, bookStatus, bookAvailability);

            bookService.addBook(newBook, CURRENT_USER);

            request.setAttribute("message", "Book '" + title + "' added successfully!");
            request.setAttribute("messageType", "success");

            request.removeAttribute("title");
            request.removeAttribute("author");
            request.removeAttribute("selectedCategory");
            request.removeAttribute("selectedStatus");
            request.removeAttribute("selectedAvailability");

            populateDropdownData(request); 
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);

        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                request.setAttribute("message", "Error: A book with the same title and category already exists.");
            } else {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("messageType", "error");
            System.err.println("LibraryException: " + e.getMessage());
            e.printStackTrace();
            populateDropdownData(request); 
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
            populateDropdownData(request); 
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
        }
    }

    private void populateDropdownData(HttpServletRequest request) {
        List<String> categories = Arrays.stream(BookCategory.values())
                                        .map(BookCategory::getDisplayName)
                                        .collect(Collectors.toList());
        request.setAttribute("bookCategories", categories);

        List<String> statuses = Arrays.stream(BookStatus.values())
                                       .map(Enum::toString)
                                       .collect(Collectors.toList());
        request.setAttribute("bookStatuses", statuses);

        List<String> availabilities = Arrays.stream(BookAvailability.values())
                                             .map(Enum::toString) 
                                             .collect(Collectors.toList());
        request.setAttribute("bookAvailabilities", availabilities);
    }
}