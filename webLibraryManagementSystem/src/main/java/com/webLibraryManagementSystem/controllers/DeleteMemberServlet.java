package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.List;

import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/DeleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteMemberServlet() {
		super();
	}

	MemberServiceImpl memberService = new MemberServiceImpl();
	BookServicesImpl bookService = new BookServicesImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		try {
			Member member = memberService.getMemberId(memberId);
			List<CustomActiveIssuedBooks> books = bookService.getActiveIssuedBooks();
			long count = books.stream().filter(isuue -> isuue.getMemberId() == member.getMemberId()).count();
			if (count > 0) {
				request.setAttribute("message", "Member have " + count + " Books Please collect books before delete");
			} else {
				memberService.deleteMember(member);
				request.setAttribute("message", member.getName() + "deleted Successfully");
			}

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("MembersViewAllServlet");
		dispatcher.forward(request, response);

	}

}
