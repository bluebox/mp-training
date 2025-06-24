package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.LibraryService;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int issuedId = Integer.parseInt(request.getParameter("issuedId"));
            LibraryService lib;
            PrintWriter out = response.getWriter();
			try {
				lib = new LibraryService();
				lib.returnBook(issuedId);
				out.println("<b>Book is returned Successfully!</b>");
			} catch (Exception e) {
				out.println("<b>Book is not returned..</b>");
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
