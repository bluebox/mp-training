package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.exceptions.InvalidException;
import com.webLibraryManagementSystem.services.MemberService;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MembersViewAllServlet")
public class MembersViewAllServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Member> members;
		try {
			members = new ArrayList<>(memberService.getMembers());
			request.setAttribute("members", members);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewMembers.jsp");
			dispatcher.forward(request, response);
		} catch (InvalidException e) {
			e.printStackTrace();
		}

	}

}
