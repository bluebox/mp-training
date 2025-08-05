package library.controllers;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import library.validation.BookValidator;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private final String CURRENT_USER = "ADMIN";

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        Book bookToUpdate = null;

        if (bookIdStr != null && !bookIdStr.isEmpty()) {
            try {
                int bookId = Integer.parseInt(bookIdStr);
                BookValidator.validateNumericId(bookId, "Book ID");
                
    	        Map<String, Object> criteria = new HashMap<>();
    	        criteria.put("bookId", bookId);
    	        List<Book> books = bookService.findBooks(criteria);
    	        bookToUpdate = books.isEmpty() ? null : books.get(0);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Book ID format.");
                request.setAttribute("messageType", "error");
            } catch (LibraryException e) {
                request.setAttribute("message", "Error retrieving book: " + e.getMessage());
                request.setAttribute("messageType", "error");
                System.err.println("Error getting book by ID: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (bookToUpdate != null) {
            request.setAttribute("bookId", bookToUpdate.getBookId());
            request.setAttribute("title", bookToUpdate.getTitle());
            request.setAttribute("author", bookToUpdate.getAuthor());
            request.setAttribute("selectedCategory", bookToUpdate.getCategory().getDisplayName());
            request.setAttribute("selectedStatus", bookToUpdate.getStatus().toString());
            request.setAttribute("selectedAvailability", bookToUpdate.getAvailability().toString());
        } else {
            if (request.getAttribute("message") == null) { 
                 request.setAttribute("message", "Book not found or no Book ID provided.");
                 request.setAttribute("messageType", "error");
            }
        }

        populateDropdownData(request); 
        request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String bookIdStr = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryDisplayName = request.getParameter("category");
        String statusSelectedString = request.getParameter("status");
        String availabilitySelectedString = request.getParameter("availability");

        request.setAttribute("bookId", bookIdStr);
        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("selectedCategory", categoryDisplayName);
        request.setAttribute("selectedStatus", statusSelectedString);
        request.setAttribute("selectedAvailability", availabilitySelectedString);

        // Basic validation for empty fields
        if (bookIdStr == null || bookIdStr.isEmpty() ||
            title == null || title.isEmpty() ||
            author == null || author.isEmpty() ||
            categoryDisplayName == null || categoryDisplayName.isEmpty() ||
            statusSelectedString == null || statusSelectedString.isEmpty() ||
            availabilitySelectedString == null || availabilitySelectedString.isEmpty()) { 

            request.setAttribute("message", "Please fill in all fields.");
            request.setAttribute("messageType", "error");
            populateDropdownData(request); 
            request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);
            BookValidator.validateNumericId(bookId, "Book ID");

            Map<String, Object> criteria = new HashMap<>();
	        criteria.put("bookId", bookId);
	        List<Book> books = bookService.findBooks(criteria);
	        Book currentBook  = books.isEmpty() ? null : books.get(0);

            if (currentBook == null) {
                request.setAttribute("message", "Error: Book with ID " + bookId + " not found for update.");
                request.setAttribute("messageType", "error");
                populateDropdownData(request);
                request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
                return;
            }

            BookCategory bookCategory = BookCategory.fromDisplayName(categoryDisplayName);
            BookStatus bookStatus = BookStatus.valueOf(statusSelectedString);
            BookAvailability bookAvailability = BookAvailability.valueOf(availabilitySelectedString); 

            currentBook.setTitle(title);
            currentBook.setAuthor(author);
            currentBook.setCategory(bookCategory);
            currentBook.setStatus(bookStatus);
            currentBook.setAvailability(bookAvailability); 

            boolean success = bookService.updateBook(currentBook, CURRENT_USER);
            if (success) {
                request.setAttribute("message", "Book ID " + currentBook.getBookId() + " updated successfully!");
                request.setAttribute("messageType", "success");
                
                request.setAttribute("bookId", currentBook.getBookId());
                request.setAttribute("title", currentBook.getTitle());
                request.setAttribute("author", currentBook.getAuthor());
                request.setAttribute("selectedCategory", currentBook.getCategory().getDisplayName());
                request.setAttribute("selectedStatus", currentBook.getStatus().toString());
                request.setAttribute("selectedAvailability", currentBook.getAvailability().toString()); 

            } else {
                request.setAttribute("message",
                        "Failed to update Book ID " + currentBook.getBookId() + ". No changes made or an issue occurred.");
                request.setAttribute("messageType", "error");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid Book ID format.");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) { 
                request.setAttribute("message", "Error: A book with the same title and category already exists.");
            } else {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("messageType", "error");
            System.err.println("LibraryException during update: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) { 
            request.setAttribute("message", "Invalid category, status, or availability selected."); 
            request.setAttribute("messageType", "error");
            System.err.println("IllegalArgumentException during update: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { 
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } finally {
            populateDropdownData(request); 
            request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
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