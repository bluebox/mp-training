package com.lms.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.model.Member;
import com.lms.service.MemberService;
import com.lms.service.impl.MemberServiceImpl;


@WebServlet("/ViewMembersServlet")
public class ViewMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService;
    private List<Member> members;
	
	public void init() {
		
		memberService = new MemberServiceImpl();
		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		PrintWriter out=response.getWriter();
		if("Update".equals(action)) {
			int memberId = Integer.parseInt(request.getParameter("memberId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile_no = request.getParameter("mobile");
			char Gender = request.getParameter("gender").charAt(0);
			String address = request.getParameter("address");
			Member member =new Member(memberId, name, email, mobile_no, Gender, address);
			request.setAttribute("member", member);
			request.setAttribute("fileToRender", "UpdateMembers.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else if("UpdateMember".equals(action)) {
			int MemberId = Integer.parseInt(request.getParameter("MemberId"));
			Member member = memberService.getMemberById(MemberId);
			member.setMember_Name(request.getParameter("MemberName").trim());
			member.setEmail(request.getParameter("email").trim());
			member.setMobile_No(request.getParameter("Mobile Number").trim());
			member.setGender(request.getParameter("gender").trim().charAt(0));
			member.setAddress(request.getParameter("address").trim());
			memberService.updateMember(member);
			members = memberService.getAllMembers();
			request.setAttribute("membersList", members);
			request.setAttribute("fileToRender", "ViewMembers.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else if("Cancel".equals(action)) {
			members = memberService.getAllMembers();
			request.setAttribute("membersList", members);
			request.setAttribute("fileToRender", "ViewMembers.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			members = memberService.getAllMembers();
			request.setAttribute("membersList", members);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
