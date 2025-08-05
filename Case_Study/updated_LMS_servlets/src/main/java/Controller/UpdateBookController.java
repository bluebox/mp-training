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
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/updatebook")
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookController() {
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
		// TODO Auto-generated method stub
		
		BookService bookservice=new BookService();
		String bookid=request.getParameter("bookid");
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		String status=request.getParameter("status");
		String availability=request.getParameter("availability"); 
		boolean res=false;
		PrintWriter out=response.getWriter();
		String message="";
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
			
			 res=bookservice.updatebookdetails(Integer.parseInt(bookid) ,new Book(title,author,category,Status.getstatus(String.valueOf(status.charAt(0))),Availability.getstatus(String.valueOf(availability.charAt(0)))));
		
		
			 if (res) {
		            message = "Book successfully added!";
		        } else {
		            message = "Something went wrong. Book was not added.";
		        }
		}
		catch (IllegalArgumentException e) {
	        message = e.getMessage();
	    } catch (Exception e) {
	        message = "Unexpected error: " + e.getMessage();
	    }
		request.setAttribute("message", message);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("addbookresult.jsp");
	    dispatcher.forward(request, response);
	}

}
