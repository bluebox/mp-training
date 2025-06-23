package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.domain.Member;
import com.library.service.LibraryService;
import com.library.service.impl.LibraryServiceImpl;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		System.out.println(name);
		System.out.println(email);
		System.out.println(mobile);
		System.out.println(gender);
		Member member= new Member(name,email,mobile,gender.charAt(0),address); 
		LibraryService service=new LibraryServiceImpl();
		System.out.println(member);
		if(service.addMember(member))
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.print("<h1> Member added Sucessfully</h1>");
		}

	}

}
