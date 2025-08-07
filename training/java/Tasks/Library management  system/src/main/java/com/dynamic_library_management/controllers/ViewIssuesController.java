package com.dynamic_library_management.controllers;

import java.io.IOException;
import java.util.List;

import com.dynamic_library_management.dao.implementation.IssueRecordDaoImplementation;
import com.dynamic_library_management.domain.IssueRecord;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewIssues")
public class ViewIssuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("inside get");
		try {

			List<IssueRecord> issues = new IssueRecordDaoImplementation().getAllIssues();
			System.out.println("Members fetched: " + issues.size());
			request.setAttribute("issues", issues);

			RequestDispatcher rd = request.getRequestDispatcher("jsp/viewIssues.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
