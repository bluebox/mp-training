package com.dynamic_library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;

import com.dynamic_library_management.exceptions.DatabaseException;
import com.dynamic_library_management.services.implementation.MemberServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteMemberController")
public class DeleteMemberController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memberId = Integer.parseInt(request.getParameter("memberId"));

		try {
			boolean deleted = new MemberServiceImplementation().deleteMemberById(memberId);
			if (deleted) {
				request.getSession().setAttribute("successMessage", "Member Deleted Successfully");
			} else {
				request.getSession().setAttribute("errorMessage", "Failed to Delete Member");
			}
		} catch (DatabaseException | SQLException e) {
			request.getSession().setAttribute("errorMessage", e.getMessage());
		}

		response.sendRedirect("viewAllMembers");
	}
}
