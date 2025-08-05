package com.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.exceptions.EmptyFieldsException;
import com.lms.model.Member;
import com.lms.service.BookService;
import com.lms.service.IssueLogService;
import com.lms.service.MemberService;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;


@WebServlet("/BooksMembersServlet")
public class BooksMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService= new BookServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	private List<Member> members;
	private List<String> books;
	private IssueLogService issueLogService = new IssueLogServiceImpl();
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void getBooks() {
		books=bookService.getAllBooks().stream().map(resultbook->resultbook.getBook_Id()+"-"+resultbook.getBook_Title()+"["+resultbook.getBook_Category()+"]").collect(Collectors.toList());
		members = List.of();
	}
	
	private void getMembers(int bookId) {
		members = issueLogService.getAllIssuedRecords().stream().filter(record -> record.getBookId()==bookId)
				 .map(record -> memberService.getMemberById(record.getMemberId())).distinct().collect(Collectors.toList());
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		if("GetMembers".equals(action)) {
			getBooks();
			try {
				if(request.getParameter("books").isEmpty()) {
					throw new EmptyFieldsException("Please select a Book");
				}
				else {
					int bookId = Integer.parseInt(request.getParameter("books").split("-")[0].trim());
					getMembers(bookId);
					request.setAttribute("membersList", members);
					String book = request.getParameter("books");
					request.setAttribute("selectedBook", book);
				}
				request.setAttribute("booksList", books);
				request.setAttribute("fileToRender", "BooksMembers.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			catch(EmptyFieldsException e) {
				out.println(e.getMessage());
			}
		}
		else {
			getBooks();
			request.setAttribute("membersList", members);
			request.setAttribute("booksList", books);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}
