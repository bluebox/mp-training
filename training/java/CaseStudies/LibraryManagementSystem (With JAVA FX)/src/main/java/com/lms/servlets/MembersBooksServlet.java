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
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.IssueLogService;
import com.lms.service.MemberService;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;


@WebServlet("/MembersBooksServlet")
public class MembersBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private List<String> members;
	private List<Book> books;
	private IssueLogService issueLogService = new IssueLogServiceImpl();
	String email="";

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
	
	private void getBooks(int memberId) {
		books = issueLogService.booksOfMember(memberId);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("Search".equals(action)) {
			getMembers();
			try {
				String emailparam = request.getParameter("email");
				if (emailparam == null) {
				    email = "";
				}
				String membersparam = request.getParameter("members");
				if(emailparam.isEmpty()) {
					if(membersparam==null) {
						throw new EmptyFieldsException("One or more of the fields are empty");
					}
					else {
						String member = request.getParameter("members");
						int memberId= Integer.parseInt(member.split("\\.")[0].trim());
						getBooks(memberId);
						request.setAttribute("membersList", members);
						request.setAttribute("selectedMember", member);
						request.setAttribute("email", request.getParameter("email"));
					}
				}
				else {
					String email=request.getParameter("email");
					Member member=memberService.getMemberByEmail(email);
					getBooks(member.getMember_Id());
					String memberField = member.getMember_Id()+". "+member.getMember_Name();
					request.setAttribute("membersList", members);
					request.setAttribute("selectedMember", memberField);
					request.setAttribute("email", email);
				}
				request.setAttribute("booksList", books);
				request.setAttribute("fileToRender", "MembersBooks.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			catch(EmptyFieldsException e) {
				getMembers();
				request.setAttribute("membersList", members);
				request.setAttribute("booksList", books);
				request.setAttribute("email", email);
				request.setAttribute("fileToRender", "MembersBooks.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
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
