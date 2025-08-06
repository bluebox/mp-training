package com.dynamic_library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dynamic_library_management.dao.implementation.BookDaoImplementation;
import com.dynamic_library_management.dao.implementation.IssueRecordDaoImplementation;
import com.dynamic_library_management.dao.implementation.MemberDaoImplementation;
import com.dynamic_library_management.domain.Book;
import com.dynamic_library_management.domain.Member;
import com.dynamic_library_management.exceptions.DatabaseException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/returnRecord")
public class ReturnRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Member> members = new MemberDaoImplementation().getAllMembers();
			request.getSession().setAttribute("members", members);
			request.setAttribute("step", "member");

			RequestDispatcher rd = request.getRequestDispatcher("jsp/returnBook.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "error");
			request.setAttribute("message", "Failed to load return form.");
			request.getRequestDispatcher("jsp/returnBook.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String selectedMemberId = request.getParameter("memberId");
		if (selectedMemberId != null && !selectedMemberId.isEmpty()) {
			int memberId = Integer.parseInt(selectedMemberId);
			System.out.println(memberId);
			try {
				List<Member> members = new MemberDaoImplementation().getAllMembers();
				request.setAttribute("members", members);
				request.setAttribute("selectedMemberId", memberId);

				List<Book> issuedBooks = new BookDaoImplementation().selectAllMemberBooks(memberId);
				request.setAttribute("books", issuedBooks);
				request.setAttribute("step", "book");
				request.getRequestDispatcher("jsp/returnBook.jsp").forward(request, response);

		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("status", "error");
			request.setAttribute("message", "Database error occurred.");
			request.getRequestDispatcher("jsp/returnBook.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "error");
			request.setAttribute("message", "Unexpected error occurred.");
			request.getRequestDispatcher("jsp/returnBook.jsp").forward(request, response);
		}
		}
	}
}