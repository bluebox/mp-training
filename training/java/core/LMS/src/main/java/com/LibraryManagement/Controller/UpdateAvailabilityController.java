package com.LibraryManagement.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.LibraryManagement.Dao.Interface.BookDAO;
import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;

/**
 * Servlet implementation class UpdateAvailabilityController
 */
@WebServlet("/UpdateAvailabilityController")
public class UpdateAvailabilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       BookServiceImplementation bsi=new BookServiceImplementation();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAvailabilityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Book book =new Book();
        int bookId = Integer.parseInt(request.getParameter("id"));

       
		try {
			book = bsi.getBookById(bookId);
			 request.setAttribute("book", book);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateAvailabilityForm.jsp");
		        dispatcher.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 

       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
