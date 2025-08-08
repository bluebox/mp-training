package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.services.BookServices;
import com.webLibraryManagementSystem.services.IssueService;
import com.webLibraryManagementSystem.services.MemberService;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.services.impl.IssueServiceImpl;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;
import com.webLibraryManagementSystem.utilities.IssueStatus;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueBookServlet
 */
@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	BookServices bookService = new BookServicesImpl();
	IssueService issueService = new IssueServiceImpl();

	public IssueBookServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Book> books = bookService.getBooks();
			request.setAttribute("booksList", books);
		} catch (InvalidException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		try {
			List<Member> members = memberService.getMembers();
			request.setAttribute("membersList", members);
		} catch (InvalidException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("issueBook.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int memberId;
		try {
			memberId = Integer.parseInt(request.getParameter("memberId"));
		} catch (NumberFormatException e) {
			memberId = 0;
		}
		int bookId;
		try {
			bookId = Integer.parseInt(request.getParameter("bookId"));
		} catch (NumberFormatException e) {
			bookId = 0;
		}
		LocalDate date = null;
		try {
			date = LocalDate.parse(request.getParameter("issueDate"));
		} catch (DateTimeParseException e) {
			request.setAttribute("errorMessage", "please enter Date dd-mm-yyyy format");
			request.setAttribute("memberSelected", memberId);
			request.setAttribute("bookSelected", bookId);
			doGet(request, response);
			return;
		}

		if (memberId != 0 && bookId != 0 && date != null) {
			IssueRecord newIssue = new IssueRecord(-1, bookId, memberId, IssueStatus.ISSUED, date, null);
			try {

				issueService.addIssue(newIssue);

				request.setAttribute("successMessage", "issued successfully");
				doGet(request, response);
				return;

			} catch (InvalidException e) {
				request.setAttribute("errorMessage", e.getMessage());

			}
		} else {
			request.setAttribute("errorMessage", "Select Book, Member and Date");

		}

		request.setAttribute("memberSelected", memberId);
		request.setAttribute("bookSelected", bookId);
		request.setAttribute("dateSelected", date);
		doGet(request, response);

	}

}
