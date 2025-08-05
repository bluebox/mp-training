package com.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.exceptions.EmptyFieldsException;
import com.lms.model.Member;
import com.lms.service.MemberService;
import com.lms.service.impl.MemberServiceImpl;


@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	
	public void init() {
		memberService = new MemberServiceImpl();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		if("Submit".equals(action)) {
			try {
				if(request.getParameter("MemberName").isEmpty() || request.getParameter("email").isEmpty() || request.getParameter("Mobile Number").isEmpty() || request.getParameter("gender").isEmpty() ||request.getParameter("address").isEmpty()) {
					throw new EmptyFieldsException("One or more of the fields are empty");
				}
				String name = request.getParameter("MemberName");
				String email = request.getParameter("email");
				String mobile_no = request.getParameter("Mobile Number");
				char Gender = request.getParameter("gender").charAt(0);
				String address = request.getParameter("address");
				Member member =new Member(name, email, mobile_no, Gender, address);
				memberService.addNewMember(member);
				request.setAttribute("alertMessage", "Succefully added member");
			}
			catch(EmptyFieldsException | IllegalArgumentException e) {
				
				request.setAttribute("alertMessage", e.getMessage());
				
			}finally {
				request.setAttribute("fileToRender", "AddMember.jsp");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
