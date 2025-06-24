package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.Member;
import Service.LibraryService;

/**
 * Servlet implementation class ViewAllMembers
 */
@WebServlet("/viewAllMembers")
public class ViewAllMembers extends HttpServlet {
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
			List<Member> members = lib.viewAllMembers();
			out.println("<h1>All Members</h1>");
			for(Member member:members) {
				out.println("<h3>Member ID : "+member.getMemberId()+"</h3>");
				out.println("<h3>Name : "+member.getName()+"</h3>");
				out.println("<h3>Email : "+member.getEmail()+"</h3>");
				out.println("<h3>Mobile : "+member.getMobile()+"</h3>");
				out.println("<h3>Gender : "+member.getGender()+"</h3>");
				out.println("<h3>Address : "+member.getAddress()+"</h3>");
				out.println("-".repeat(40));
			}
		} catch (Exception e) {
			out.println("<html><body><b>No Memebers there!</b></body></html>");
			e.printStackTrace();
		}

	}

	

}
