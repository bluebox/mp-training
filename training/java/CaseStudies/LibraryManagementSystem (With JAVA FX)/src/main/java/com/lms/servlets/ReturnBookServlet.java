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

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService= new BookServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	private IssueLogService issueLogService = new IssueLogServiceImpl();
	private List<String> members;
	private List<String> books;
	private String email="";
      
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void getMembers() {
		members=memberService.getAllMembers().stream().map(member-> member.getMember_Id()+". "+member.getMember_Name()).collect(Collectors.toList());
		email="";
		books = List.of();
	}
	
	private void getMemberBooks(String member) {
		int memberId =  Integer.parseInt(member.split("\\.")[0].trim());
		books= issueLogService.getAllIssuedRecords().stream()
				.filter(r->{
					return r.getMemberId() == memberId && r.getStatus() == 'I';
				})
				.map(r->{
					return r.getIssueId()+"-"+r.getBookId()+" - " +bookService.getBookById(r.getBookId()).getBook_Title();
				})
				.collect(Collectors.toList());
	}
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		
		if("Search".equals(action)) {
			try {
				if(request.getParameter("email").isEmpty()) {
					if(request.getParameter("members").isEmpty()) {
						throw new EmptyFieldsException("One or more of the fields are empty");
					}
					else {
						String member= request.getParameter("members");
						getMemberBooks(member);
						request.setAttribute("membersList", members);
						request.setAttribute("selectedMember", member);
						request.setAttribute("email", email);
					}
				}
				else {
					String email=request.getParameter("email");
					Member member=memberService.getMemberByEmail(email);
					String memberField = member.getMember_Id()+". "+member.getMember_Name();
					request.setAttribute("membersList", members);
					request.setAttribute("selectedMember", memberField);
					request.setAttribute("email", email);
					getMemberBooks(memberField);
				}
				request.setAttribute("booksList", books);
				request.setAttribute("fileToRender", "ReturnBook.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			catch(EmptyFieldsException e) {
				out.println(e.getMessage());
			}
		}
		else if("Return".equals(action)) {
			try {
				System.out.println("Trying to return");
				if(request.getParameter("members").isEmpty() || request.getParameter("books").isEmpty()) {
					throw new EmptyFieldsException("One or more of the fields are empty");
				}
				String book=request.getParameter("books");
				int bookId = Integer.parseInt(book.split("-")[1].trim());
				int issueId = Integer.parseInt(book.split("-")[0].trim());
				bookService.updateBookAvailability(bookId,true);
				issueLogService.returnIssuedBook(issueId, true);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} catch (EmptyFieldsException e) {
				out.println(e.getMessage());
			} 
		}
		else if("Cancel".equals(action)) {
			
			getMembers();
			request.setAttribute("membersList", members);
			request.setAttribute("booksList", books);
			request.setAttribute("email", email);
			request.setAttribute("fileToRender", "ReturnBook.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			getMembers();
			request.setAttribute("membersList", members);
			request.setAttribute("booksList", books);
			request.setAttribute("email", email);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}
