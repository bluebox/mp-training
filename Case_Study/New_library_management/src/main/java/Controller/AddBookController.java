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
		request.getRequestDispatcher("/WEB-INF/views/addbook.jsp").forward(request, response);
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
		int res=0;
		try {
			 res=bookservice.addbooks(new Book(title,author,category,Status.getstatus(String.valueOf(status.charAt(0))),Availability.getstatus(String.valueOf(availability.charAt(0)))));
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out=response.getWriter();
		if(res>0) {
			
			out.println("<h1>Successfully added book</h1>");
			out.println("<form action='books.html' method='get' style='display:inline;'>");
			out.println("<button type='submit'>Back to Books</button>");
			out.println("</form>");
		}
		else
			out.println("<h1>Something is wrong, not added</h1>");
//		 out.println("<form action='addbook.jsp' method='get' style='display:inline;'>");
//		out.println("<button type='submit'>Add Book</button>");
//		out.println("</form><br>");


	}

}
