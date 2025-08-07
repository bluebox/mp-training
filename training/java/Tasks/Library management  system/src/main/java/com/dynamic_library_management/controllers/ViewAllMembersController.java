package com.dynamic_library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dynamic_library_management.domain.Member;
import com.dynamic_library_management.exceptions.DatabaseException;
import com.dynamic_library_management.services.implementation.MemberServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewAllMembers")
public class ViewAllMembersController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Member> members = new MemberServiceImplementation().validateViewAllMembers();
			request.setAttribute("members", members);
			request.getRequestDispatcher("jsp/viewAllMembers.jsp").forward(request, response);
		} catch (DatabaseException e) {
			request.setAttribute("errorMessage", "Cannot Fetch Members Data...");
			request.getRequestDispatcher("jsp/viewAllMembers.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
