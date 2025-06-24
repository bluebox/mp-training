package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.Book;
import Pojo.Member;
import Service.LibraryService;

/**
 * Servlet implementation class IssueBook
 */
@WebServlet("/issueBook")
public class IssueBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int memberId = Integer.parseInt(request.getParameter("memberId"));
            LibraryService lib;
            PrintWriter out = response.getWriter();
			try {
				lib = new LibraryService();
				lib.issueBook(bookId,memberId);
				out.println("<b>Book issued Successfully!</b>");
			} catch (Exception e) {
				out.println("<b>Book not issued..</b>");
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
