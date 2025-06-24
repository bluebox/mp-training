package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.IssueRecord;
import Service.LibraryService;

/**
 * Servlet implementation class IssuedRecords
 */
@WebServlet("/issuedRecords")
public class IssuedRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LibraryService lib;
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		try {
			lib = new LibraryService();
			List<IssueRecord> records = lib.viewIssuedRecords();
			out.println("<h1>All records</h1>");
			for(IssueRecord record:records) {
				out.println("<h3>Issued ID : "+record.getIssueId()+"</h3>");
				out.println("<h3>Book ID : "+record.getBookId()+"</h3>");
				out.println("<h3>Member ID : "+record.getMemberId()+"</h3>");
				out.println("<h3>Status : "+record.getStatus()+"</h3>");
				out.println("<h3>Issue Date : "+record.getIssueDate()+"</h3>");
				out.println("<h3>Return Date : "+record.getReturnDate()+"</h3>");
				out.println("-".repeat(40));
			}
		} catch (Exception e) {
			out.println("<html><body><b>No records there..</b></body></html>");
			e.printStackTrace();
		}
	}

	

}
