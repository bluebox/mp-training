package com.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.lms.exceptions.EmptyFieldsException;
import com.lms.exceptions.IdNotExistException;
import com.lms.model.Issue_Records;
import com.lms.model.Member;
import com.lms.service.BookService;
import com.lms.service.IssueLogService;
import com.lms.service.MemberService;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;


@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService= new BookServiceImpl();
	private MemberService memberService = new MemberServiceImpl();
	private IssueLogService issueLogService = new IssueLogServiceImpl();
	private List<String> members;
	private List<String> books;
	private String email="";
	
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void getMembersAndBooks() {
		members=memberService.getAllMembers().stream().map(member-> member.getMember_Id()+". "+member.getMember_Name()).collect(Collectors.toList());

		books=bookService.getAllBooks().stream().filter(book-> ("A".equals(String.valueOf(book.getBook_Status())) && "A".equals(String.valueOf(book.getBook_Availability()))))
				.map(resultbook->resultbook.getBook_Id()+"-"+resultbook.getBook_Title()+"["+resultbook.getBook_Category()+"]").collect(Collectors.toList());
		
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String action=req.getParameter("action");
		PrintWriter out=res.getWriter();
		if("Search".equals(action)) {
			try {
				if(req.getParameter("email").isEmpty()) {
					throw new EmptyFieldsException("One or more of the fields are empty");
				}
				String email=req.getParameter("email");
				Member member=memberService.getMemberByEmail(email);
				String memberField = member.getMember_Id()+". "+member.getMember_Name();
				req.setAttribute("membersList", members);
				req.setAttribute("selectedMember", memberField);
				req.setAttribute("email", email);
				req.setAttribute("booksList", books);
				req.setAttribute("fileToRender", "IssueBook.jsp");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			}
			catch(EmptyFieldsException e) {
				out.println(e.getMessage());
			}
		}
		else if("Issue".equals(action)) {
			try {
				if(req.getParameter("members").isEmpty() || req.getParameter("books").isEmpty()) {
					throw new EmptyFieldsException("One or more of the fields are empty");
				}
				String member = req.getParameter("members");
				String book = req.getParameter("books");
				Issue_Records record=new Issue_Records(Integer.parseInt(book.split("-")[0].trim()),Integer.parseInt(member.split("\\.")[0].trim()));
				issueLogService.addIssueRecord(record);
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, res);
			} catch (EmptyFieldsException | IdNotExistException e) {
				out.println(e.getMessage());
			} 
		}
		else if("Cancel".equals(action)) {
			
			getMembersAndBooks();
			req.setAttribute("membersList", members);
			req.setAttribute("booksList", books);
			req.setAttribute("email", email);
			req.setAttribute("fileToRender", "IssueBook.jsp");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
		}
		else {
			getMembersAndBooks();
			req.setAttribute("membersList", members);
			req.setAttribute("booksList", books);
			req.setAttribute("email", email);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
		}
	}
	
	@Override
	public void destroy() {
		
	}

}
