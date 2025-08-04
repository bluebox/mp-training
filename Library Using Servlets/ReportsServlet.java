package com.library.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.library.dto.ActiveMember;
import com.library.dto.CategoryCount;
import com.library.dto.OverdueBook;


import com.library.dao.BookDAOImplementation;
import com.library.model.Book;
import com.library.model.IssueRecords;
import com.library.model.Member;
import com.library.service.BookServiceImplementation;
import com.library.service.IssueRecordServiceImplementation;
import com.library.service.MemberServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReportsServlet")
public class ReportsServlet extends HttpServlet {

	private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
	private final BookServiceImplementation bookService = new BookServiceImplementation(new BookDAOImplementation());

	private final MemberServiceImplementation memberService = new MemberServiceImplementation();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("Main.html");
			return;
		}

		try {
			switch (action) {
			case "overdue":
				loadOverdueBooks(request, response);
				break;
			case "categoryCount":
				loadBooksPerCategory(request, response);
				break;
			case "activeMembers":
				loadActiveIssuedMembers(request, response);
				break;
			default:
				response.sendRedirect("Main.html");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/Main.html").forward(request, response);
		}
	}

	private void loadOverdueBooks(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<IssueRecords> allIssues = issueService.getAllIssues();
		List<IssueRecords> overdue = allIssues.stream().filter(issue -> issue.getReturnDate() == null)
				.filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(1)))
				.collect(Collectors.toList());

		List<OverdueBook> overdueBooks = new ArrayList<>();
		for (IssueRecords issue : overdue) {
			Book book = bookService.getBookById(issue.getBookId());
			Member member = memberService.fetchMemberById(issue.getMemberId());
			overdueBooks.add(new OverdueBook(book.getTitle(), member.getName(), issue.getIssueDate()));
		}

		request.setAttribute("overdueBooks", overdueBooks);
		
		request.getRequestDispatcher("/overdueBooks.jsp").forward(request, response);
	}

	private void loadBooksPerCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Book> allBooks = bookService.getAllBooks();
		Map<String, Long> countByCategory = allBooks.stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

		List<CategoryCount> categoryCounts = countByCategory.entrySet().stream()
				.map(entry -> new CategoryCount(entry.getKey(), entry.getValue()))
				.sorted(Comparator.comparingLong(CategoryCount::getCount).reversed()).collect(Collectors.toList());

		request.setAttribute("categoryCounts", categoryCounts);
		request.getRequestDispatcher("/categoryCount.jsp").forward(request, response);
	}

	private void loadActiveIssuedMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<IssueRecords> allIssues = issueService.getAllIssues();

		Map<Integer, Long> memberIssueCount = allIssues.stream().filter(issue -> issue.getReturnDate() == null)
				.collect(Collectors.groupingBy(IssueRecords::getMemberId, Collectors.counting()));

		List<ActiveMember> activeMembers = new ArrayList<>();
		for (Map.Entry<Integer, Long> entry : memberIssueCount.entrySet()) {
			Member member = memberService.fetchMemberById(entry.getKey());
			activeMembers.add(new ActiveMember(member.getName(), entry.getValue().intValue()));
		}

		request.setAttribute("activeMembers", activeMembers);
		request.getRequestDispatcher("/activeMembers.jsp").forward(request, response);
	}

	// --- Data holders ---
	public static class OverdueBook {
		private final String title;
		private final String memberName;
		private final LocalDate issueDate;

		public OverdueBook(String title, String memberName, LocalDate issueDate) {
			this.title = title;
			this.memberName = memberName;
			this.issueDate = issueDate;
		}

		public String getTitle() {
			return title;
		}

		public String getMemberName() {
			return memberName;
		}

		public LocalDate getIssueDate() {
			return issueDate;
		}
	}

	public static class CategoryCount {
		private final String category;
		private final Long count;

		public CategoryCount(String category, Long count) {
			this.category = category;
			this.count = count;
		}

		public String getCategory() {
			return category;
		}

		public Long getCount() {
			return count;
		}
	}

	public static class ActiveMember {
		private final String name;
		private final int booksIssued;

		public ActiveMember(String name, int booksIssued) {
			this.name = name;
			this.booksIssued = booksIssued;
		}

		public String getName() {
			return name;
		}

		public int getBooksIssued() {
			return booksIssued;
		}
	}
}
