package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

import Service.BookService;
import domain.Book;
import domain.checking_enum.Availability;
import domain.checking_enum.Status;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet("/addbook")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookservice=new BookService();
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		String status=request.getParameter("status");
		String availability=request.getParameter("availability"); 
		boolean res=false;
		String message="";
		PrintWriter out=response.getWriter();
		try {
		if (title == null || title.trim().isEmpty()
                || author == null || author.trim().isEmpty()
                || category == null || category.trim().isEmpty()) {
            message="Please fill in Title, Author, and Category fields.";
            throw new IllegalArgumentException("Please fill in Title, Author, and Category fields.");
 
        }

        if (status == null) {
            throw new IllegalArgumentException("Please fill in Status fields.");

        }

        if (availability == null) {
            throw new IllegalArgumentException("Please fill in Availability fields.");

        }
		
		
			 res=bookservice.addbooks(new Book(title,author,category,Status.getstatus(String.valueOf(status.charAt(0))),Availability.getstatus(String.valueOf(availability.charAt(0)))));
			 
			 
			 if (res) {
		            message = "Book successfully added!";
		        } else {
		            message = "Something went wrong. Book was not added.";
		        }
		}
		catch (IllegalArgumentException e) {
	        message = e.getMessage();
	    } catch (SQLIntegrityConstraintViolationException e) {
	        message = "Duplicate Entry: A book with this title already exists.";
	    } catch (Exception e) {
	        message = "Unexpected error: " + e.getMessage();
	    }
		 
		
		 request.setAttribute("message", message);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("addbookresult.jsp");
		    dispatcher.forward(request, response);
	}

}
