package Controller;

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
		
			 res=bookservice.updatebookdetails(Integer.parseInt(bookid) ,new Book(title,author,category,Status.getstatus(String.valueOf(status.charAt(0))),Availability.getstatus(String.valueOf(availability.charAt(0)))));
		
		PrintWriter out=response.getWriter();
		if(res) {
			
			out.println("<h1>Successfully updated book</h1>");
		}
		else
			out.println("<h1>Something is wrong, not updated</h1>");
	}

}
