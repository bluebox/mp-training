package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

import Service.ServiceLayer;
import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/updatebook")
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServiceLayer service=new ServiceLayer();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String bookid=request.getParameter("bookid");
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		String status=request.getParameter("status");
		String availability=request.getParameter("availability"); 
		
		try {
	service.updateBookDetails(new Book(Integer.parseInt(bookid),title,author,category,BookStatus.getStatus(status.substring(0,1)),BookAvailability.getAvailability(availability.substring(0,1))));
	request.setAttribute("message", "updated Book Successfully");
	request.getRequestDispatcher("updatebook.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("updatebook.jsp").forward(request, response);
		}
	}	

}
