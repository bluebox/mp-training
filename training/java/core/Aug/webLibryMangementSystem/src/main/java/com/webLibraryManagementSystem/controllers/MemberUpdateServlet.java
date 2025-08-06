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

@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberIdParam = request.getParameter("memberId");
		if (memberIdParam != null && !memberIdParam.isEmpty()) {

			try {
				int memberId = Integer.parseInt(memberIdParam);
				Member selectedMember = memberService.getMemberId(memberId);
				loadSelectedMember(request, selectedMember);

			} catch (InvalidException e) {
				request.setAttribute("errorMessage", e.getMessage());
			} catch (NumberFormatException e1) {
				request.setAttribute("errorMessage", "invalid MemberId");
			}

		}

		loadMembers(request);
		request.getRequestDispatcher("updateMember.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberIdParam = request.getParameter("memberId");
		int memberId = Integer.parseInt(memberIdParam);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long mobile = 0L;
		try {
			mobile = Long.parseLong(request.getParameter("mobile"));
		} catch (NumberFormatException ignored) {
		}
		MemberGender gender = request.getParameter("gender") != null && !request.getParameter("gender").isEmpty()
				? MemberGender.valueOf(request.getParameter("gender"))
				: null;
		String address = request.getParameter("address");
		Member newMember = null;
		try {
			List<Member> membersList = memberService.getMembers();
			Member oldMember = membersList.stream().filter(m -> m.getMemberId() == memberId).findFirst().orElse(null);

			if (oldMember == null) {
				request.setAttribute("errorMessage", "Member not found");
			} else {
				newMember = new Member(memberId, name, email, mobile, gender, address);
				if (!newMember.equals(oldMember)) {
					memberService.updateMember(newMember, oldMember);
					request.setAttribute("successMessage", oldMember.getName() + " updated successfully.");
					loadMembers(request);
					request.getRequestDispatcher("updateMember.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("errorMessage", "Please edit at least one field before updating.");
				}
			}

		} catch (InvalidException e) {
			request.setAttribute("errorMessage", e.getMessage());

		}

		loadSelectedMember(request, newMember);
		loadMembers(request);
		request.getRequestDispatcher("updateMember.jsp").forward(request, response);
	}

	private void loadMembers(HttpServletRequest request) {
		try {

			List<Member> membersList = memberService.getMembers();
			request.setAttribute("membersList", membersList);

		} catch (InvalidException e) {

			request.setAttribute("errorMessage", e.getMessage());
		}
	}

	private void loadSelectedMember(HttpServletRequest request, Member member) {

		request.setAttribute("name", member.getName());
		request.setAttribute("email", member.getEmail());
		request.setAttribute("mobile", member.getMobile());
		request.setAttribute("genderSelected", member.getGender());
		request.setAttribute("address", member.getAddress());
		request.setAttribute("memberIdSet", member.getMemberId());

		List<MemberGender> gendersList = new ArrayList<>(Arrays.asList(MemberGender.values()));
		request.setAttribute("genderList", gendersList);
	}
}
