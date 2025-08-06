package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.services.BookServices;
import com.webLibraryManagementSystem.services.IssueService;
import com.webLibraryManagementSystem.services.MemberService;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.services.impl.IssueServiceImpl;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	BookServices bookService = new BookServicesImpl();
	IssueService issueService = new IssueServiceImpl();

	public ReturnBookServlet() {
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("returnBook.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Book book = null;
		try {
			book = bookService.getBookById(Integer.parseInt(request.getParameter("bookId")));
		} catch (NumberFormatException | InvalidException e) {
			e.printStackTrace();
		}
		Member member = null;
		try {
			member = memberService.getMemberId(Integer.parseInt(request.getParameter("memberId")));
		} catch (NumberFormatException | InvalidException e) {
			e.printStackTrace();
		}
		LocalDate date = null;
		try {
			date = LocalDate.parse(request.getParameter("returnDate"));
		} catch (DateTimeParseException e) {

			request.setAttribute("errorMessage", "please enter Date dd-mm-yyyy format");
			request.setAttribute("memberSelected", member.getMemberId());
			request.setAttribute("bookSelected", book.getBookId());

			doGet(request, response);
			return;
		}

		if (book != null && date != null && member != null) {
			try {

				issueService.returnBook(book, member.getMemberId(), date);

				request.setAttribute("successMessage", book.getTitle() + " Book is returnd by" + member.getName());
				doGet(request, response);
				return;

			} catch (InvalidException e) {
				request.setAttribute("errorMessage", e.getMessage());

			}
		} else {
			request.setAttribute("errorMessage", "Select Book, Member and Date");
		}

		request.setAttribute("memberSelected", member.getMemberId());
		request.setAttribute("bookSelected", book.getBookId());
		request.setAttribute("dateSelected", date);
		doGet(request, response);

	}

}
