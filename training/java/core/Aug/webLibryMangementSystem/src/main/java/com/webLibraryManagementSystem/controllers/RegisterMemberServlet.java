package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.services.MemberService;
import com.webLibraryManagementSystem.services.impl.MemberServiceImpl;
import com.webLibraryManagementSystem.utilities.MemberGender;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterMemberServlet")
public class RegisterMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	@Override
	public void init() throws ServletException {

		memberService = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemberGender> gendersList = new ArrayList<>(Arrays.asList(MemberGender.values()));
		req.setAttribute("genderList", gendersList);
		req.getRequestDispatcher("registerMember.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Long mobile = Long.parseLong(request.getParameter("mobile"));
		String genderStr = request.getParameter("gender");
		MemberGender gender = MemberGender.valueOf(genderStr.toUpperCase());
		String address = request.getParameter("address");

		Member member = new Member(-1, name, email, mobile, gender, address);

		int isRegistered = 0;

		try {
			isRegistered = memberService.registerMember(member);

			if (isRegistered > 0) {

				request.setAttribute("successMessage", "Member registered successfully!");
				request.getRequestDispatcher("registerMember.jsp").forward(request, response);
			} else {

				request.setAttribute("errorMessage", "Registration failed. Try again.");
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				request.setAttribute("mobile", mobile);
				request.setAttribute("genderSelected", gender);
				request.setAttribute("address", address);
				doGet(request, response);
			}
		} catch (InvalidException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("name", name);
			request.setAttribute("email", email);
			request.setAttribute("mobile", mobile);
			request.setAttribute("genderSelected", gender);
			request.setAttribute("address", address);
			doGet(request, response);
		}

	}
}
