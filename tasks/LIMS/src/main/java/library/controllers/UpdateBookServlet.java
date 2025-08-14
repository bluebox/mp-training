package library.controllers;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                int bookId = Integer.parseInt(bookIdStr);
                BookValidator.validateNumericId(bookId, "Book ID");
                
    	        Map<String, Object> criteria = new HashMap<>();
    	        criteria.put("bookId", bookId);
    	        List<Book> books = bookService.findBooks(criteria);
    	        bookToUpdate = books.isEmpty() ? null : books.get(0);
        }

        if (bookToUpdate != null) {

         	 request.setAttribute("selectedBooktoUpdate", bookToUpdate);
             
        } else {
            if (request.getAttribute("message") == null) { 
                 request.setAttribute("message", "Book not found or no Book ID provided.");
                 request.setAttribute("messageType", "error");
            }
        }

        request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	request.removeAttribute("message");
        request.removeAttribute("messageType");

        String bookIdStr = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryDisplayName = request.getParameter("category");
        String statusSelectedString = request.getParameter("status");
        String availabilitySelectedString = request.getParameter("availability");
        
     
        Book selectedBooktoUpdate = new Book();
        try {
            selectedBooktoUpdate.setBookId(Integer.parseInt(bookIdStr));
        } catch (NumberFormatException e) {
        }
        
        selectedBooktoUpdate.setTitle(title);
        selectedBooktoUpdate.setAuthor(author);
        
        if (categoryDisplayName != null && !categoryDisplayName.isEmpty()) {
        	selectedBooktoUpdate.setCategory(BookCategory.fromDisplayName(categoryDisplayName));
        }
        if (statusSelectedString != null && !statusSelectedString.isEmpty()) {
        	selectedBooktoUpdate.setStatus(BookStatus.valueOf(statusSelectedString));
        }
        if (availabilitySelectedString != null && !availabilitySelectedString.isEmpty()) {
        	selectedBooktoUpdate.setAvailability(BookAvailability.valueOf(availabilitySelectedString));
        }
        request.setAttribute("selectedBooktoUpdate", selectedBooktoUpdate);
                
        
        if (bookIdStr == null || bookIdStr.isEmpty() ||
            title == null || title.isEmpty() ||
            author == null || author.isEmpty() ||
            categoryDisplayName == null || categoryDisplayName.isEmpty() ||
            statusSelectedString == null || statusSelectedString.isEmpty() ||
            availabilitySelectedString == null || availabilitySelectedString.isEmpty()) { 

            request.setAttribute("message", "Please fill in all fields.");
            request.setAttribute("messageType", "error");
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
                request.setAttribute("message", " Book with ID " + bookId + " not found for update.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
                return;
            }

            BookCategory bookCategory = BookCategory.fromDisplayName(categoryDisplayName);
            BookStatus bookStatus = BookStatus.valueOf(statusSelectedString);

            currentBook.setTitle(title);
            currentBook.setAuthor(author);
            currentBook.setCategory(bookCategory);
            currentBook.setStatus(bookStatus);

            boolean success = bookService.updateBook(currentBook, CURRENT_USER);
            if (success) {
                request.setAttribute("message", "Book ID " + currentBook.getBookId() + " updated successfully!");
                request.setAttribute("messageType", "success");
                
                
                request.setAttribute("selectedBooktoUpdate", currentBook);

            } else {					
            		request.setAttribute("message", "Failed to update Book ID " + currentBook.getBookId() + ". Found no changes made or  Availability cannot be changed here. ");
            		request.setAttribute("messageType", "error");
            		
//                    request.setAttribute("selectedBooktoUpdate", currentBook);
               }
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) { 
                request.setAttribute("message", " A book with the same title and category already exists.");
            } else {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) { 
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("/UpdateBookForm.jsp").forward(request, response);
        }
    }


}