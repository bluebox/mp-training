package library.controllers;

import java.io.IOException;
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
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/viewBooks")
public class ViewBooksServlet extends HttpServlet {
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
        loadBooks(request);
        
//        Map<String, Object> criteria1 = new HashMap<String, Object>();
//        criteria1.put("bookId", 11);
//        Book b1 = bookService.findBooks(criteria1).get(0);
//
//        Map<String, Object> criteria2 = new HashMap<String, Object>();
//        criteria2.put("bookId", 24);
//        Book b2 = bookService.findBooks(criteria2).get(0);
//        	
//        System.out.println(b1.hashCode()==b2.hashCode());
        
        request.getRequestDispatcher("/ViewBooksScreen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No action specified.");
            request.setAttribute("messageType", "error");
            loadBooks(request);
            request.getRequestDispatcher("/ViewBooksScreen.jsp").forward(request, response);
            return;
        }

        try {
            if (action.equals("refreshBooks")) {
            	loadBooks(request);
            	request.setAttribute("message", "Books refreshed");
                request.setAttribute("messageType", null);
                
            } else if (action.startsWith("deleteBook:")) {
                int bookId = Integer.parseInt(action.split(":")[1]);
                handleDeleteBookRow(bookId, request);
               
            } else if (action.startsWith("changeAvailability:")) {
                String[] parts = action.split(":");
                int bookId = Integer.parseInt(parts[1]);
                String newAvailabilityCode = parts[2];
                BookAvailability newAvailability = BookAvailability.fromCode(newAvailabilityCode);
                updateBookAvailabilityRow(bookId, newAvailability, request);
                
            } else if (action.equals("deleteBatch")) {
                handleDeleteSelectedBooks(request);
                
            } else if (action.equals("updateAvailabilityBatch")) {
                handleUpdateAvailSelectedBooks(request);
                
            } else {
                request.setAttribute("message", "Unknown action: " + action);
                request.setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } 

        loadBooks(request);
        request.getRequestDispatcher("/ViewBooksScreen.jsp").forward(request, response);
    }

    private void loadBooks(HttpServletRequest request) throws LibraryException {
        try {
            List<Book> books = bookService.findBooks(null);
            request.setAttribute("books", books);
        } catch (LibraryException e) {
            request.setAttribute("message", "error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
    }

    private void handleDeleteBookRow(int bookId, HttpServletRequest request) throws LibraryException {
        try {
            boolean deleted = bookService.deleteBooks(Arrays.asList(bookId));
            if (deleted) {
                request.setAttribute("message", "Book ID " + bookId + " deleted successfully.");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to delete book ID " + bookId + ". Book not found.");
                request.setAttribute("messageType", "error");
            }
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
        }
    }

    private void updateBookAvailabilityRow(int bookId, BookAvailability newAvailability, HttpServletRequest request) throws LibraryException {
        
    	String statusText = newAvailability.toString().toLowerCase();
        try {
            boolean updated = bookService.updateBookAvailability(Arrays.asList(bookId), CURRENT_USER);
            if (updated) {
                request.setAttribute("message", "Book ID " + bookId + " marked as " + statusText + ".");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to mark book ID " + bookId + " as " + statusText + ". Book not found.");
                request.setAttribute("messageType", "error");
            }
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
        }
    }

    private void handleDeleteSelectedBooks(HttpServletRequest request) throws LibraryException {
        String[] selectedIds = request.getParameterValues("selectedBookIds");
        if (selectedIds == null || selectedIds.length == 0) {
            request.setAttribute("message", "Please select books to delete.");
            request.setAttribute("messageType", "error");
            return;
        }

        List<Integer> bookIdsToDelete = Arrays.stream(selectedIds)
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());

        try {
        	
            boolean results = bookService.deleteBooks(bookIdsToDelete);
            if (results) {
                request.setAttribute("message", " books with ids -> " + bookIdsToDelete +" deleted successfully.");
                request.setAttribute("messageType", "success");
            } else {
                 request.setAttribute("message", "Some books could not be deleted.");
                 request.setAttribute("messageType", "error");
            }
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
        }
    }

    private void handleUpdateAvailSelectedBooks(HttpServletRequest request) throws LibraryException {
        String[] selectedIds = request.getParameterValues("selectedBookIds");
        if (selectedIds == null || selectedIds.length == 0) {
            request.setAttribute("message", "Please select books to update availability.");
            request.setAttribute("messageType", "error");
            return;
        }

        List<Integer> bookIdsToUpdate = Arrays.stream(selectedIds)
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());

        try {
            boolean results = bookService.updateBookAvailability(bookIdsToUpdate, CURRENT_USER);

            if(results) {
                request.setAttribute("message", bookIdsToUpdate.size() + " books' availability updated successfully.");
                request.setAttribute("messageType", "success");
            } else {
                 request.setAttribute("message", "Some books' availability could not be updated.");
                 request.setAttribute("messageType", "error");
            }
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
        }
    }
}