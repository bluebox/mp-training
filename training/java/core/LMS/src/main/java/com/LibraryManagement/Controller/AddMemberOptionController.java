package com.LibraryManagement.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;

@WebServlet("/AddMemberOptionController")
public class AddMemberOptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberServiceImplementation msi = new MemberServiceImplementation();

	public AddMemberOptionController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("addmember".equalsIgnoreCase(action)) {
			request.getRequestDispatcher("AddMember.jsp").forward(request, response);
		}

		if ("viewmembers".equalsIgnoreCase(action)) {
			List<Member> updatedList = msi.getAllMembers();

			request.setAttribute("membersList", updatedList);

			request.getRequestDispatcher("ViewMembers.jsp").forward(request, response);
		}
		
		   

		  

	}
}
