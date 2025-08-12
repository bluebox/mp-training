package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.webLibraryManagementSystem.dao.ReportsDao;
import com.webLibraryManagementSystem.dao.impl.ReportsDaoImpl;
import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.InvalidException;
import com.webLibraryManagementSystem.exceptions.InvalidIssueDataException;
import com.webLibraryManagementSystem.exceptions.InvalidMemberDataException;
import com.webLibraryManagementSystem.services.BookServices;
import com.webLibraryManagementSystem.services.IssueService;
import com.webLibraryManagementSystem.services.MemberService;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.services.impl.IssueServiceImpl;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookStatus;
import com.webLibraryManagementSystem.utilities.IssueStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/issue/*")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	BookServices bookService = new BookServicesImpl();
	IssueService issueService = new IssueServiceImpl();
	private final ReportsDao reportsService = new ReportsDaoImpl();

	public IssueServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		switch (pathInfo) {
		case "/issueBook":
			issueBookGet(request, response);
			break;
		case "/returnBook":
			returnBookGet(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		if (pathInfo == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		switch (pathInfo) {
		case "/issueBook":
			issueBookPost(request, response);
			break;
		case "/returnBook":
			returnBookPost(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void issueBookGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Book> books;

			books = bookService.getBooks().stream().filter(
					b -> b.getStatus() == BookStatus.ACTIVE && b.getAvailability() == BookAvailability.AVAILABLE)
					.collect(Collectors.toList());

			request.setAttribute("booksList", books);

			List<Member> members;

			members = memberService.getMembers();

			request.setAttribute("membersList", members);

		} catch (DatabaseOperationException | InvalidException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/issueBook.jsp");
		dispatcher.forward(request, response);
	}

	protected void issueBookPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int memberId = parseId(request.getParameter("memberHiden"));
		int bookId = parseId(request.getParameter("bookHiden"));

		LocalDate date = null;
		try {
			date = LocalDate.parse(request.getParameter("issueDate"));
		} catch (DateTimeParseException e) {
			request.setAttribute("errorMessage", "Please enter date in yyyy-mm-dd format");
			issueBookGet(request, response);
			return;
		}

		IssueRecord newIssue = new IssueRecord(-1, bookId, memberId, IssueStatus.ISSUED, date, null);
		try {
			issueService.addIssue(newIssue);
			request.setAttribute("successMessage", "Book issued successfully!");

			request.setAttribute("memberSelected", null);
			request.setAttribute("bookSelected", null);
			request.setAttribute("dateSelected", "");
			request.setAttribute("memberNameTyped", "");
			request.setAttribute("book", "");

			List<Book> books = bookService.getBooks().stream().filter(
					b -> b.getStatus() == BookStatus.ACTIVE && b.getAvailability() == BookAvailability.AVAILABLE)
					.collect(Collectors.toList());
			request.setAttribute("booksList", books);

			request.setAttribute("membersList", memberService.getMembers());

			request.getRequestDispatcher("/issueBook.jsp").forward(request, response);
			return;

		} catch (InvalidException | BookNotFoundException | DatabaseOperationException | InvalidIssueDataException
				| InvalidMemberDataException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		issueBookGet(request, response);
	}

	protected void returnBookGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		loadLists(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/returnBook.jsp");
		dispatcher.forward(request, response);
	}

	protected void returnBookPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberIdStr = request.getParameter("memberId");
		String bookIdStr = request.getParameter("bookId");
		String returnDateStr = request.getParameter("returnDate");

		Integer memberId = parseId(memberIdStr);
		Integer bookId = parseId(bookIdStr);
		LocalDate returnDate = parseDate(returnDateStr);

		try {

			issueService.returnBook(bookId, memberId, returnDate);
			Book book = bookService.getBookById(bookId);
			request.setAttribute("successMessage", book.getTitle() + " book returned ");
			request.setAttribute("memberSelected", null);
			request.setAttribute("bookSelected", null);
			request.setAttribute("dateSelected", null);
			request.setAttribute("memberId", null);
			request.setAttribute("bookId", null);

			loadLists(request);

		} catch (BookNotFoundException | DatabaseOperationException | InvalidIssueDataException
				| InvalidMemberDataException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("dateSelected", returnDateStr);
			loadLists(request);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/returnBook.jsp");
		dispatcher.forward(request, response);
	}

	private int parseId(String param) {
		try {
			return Integer.parseInt(param);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private LocalDate parseDate(String dateStr) {
		try {
			return LocalDate.parse(dateStr);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	private void loadLists(HttpServletRequest request) {

		try {
			List<Book> books = bookService.getBooks().stream().filter(
					b -> b.getStatus().equals(BookStatus.ACTIVE) && b.getAvailability().equals(BookAvailability.ISSUED))
					.collect(Collectors.toList());

			request.setAttribute("booksList", books);

		} catch (DatabaseOperationException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		try {
			List<Integer> issuedMemberIds = reportsService.getActiveIssuedBooks().stream().map(i -> i.getMemberId())
					.collect(Collectors.toList());

			List<Member> members = memberService.getMembers().stream()
					.filter(m -> issuedMemberIds.contains(m.getMemberId())).collect(Collectors.toList());

			request.setAttribute("membersList", members);

		} catch (InvalidException | DatabaseOperationException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
	}
}
