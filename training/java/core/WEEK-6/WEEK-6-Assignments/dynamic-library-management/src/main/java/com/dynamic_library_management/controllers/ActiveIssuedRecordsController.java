package com.dynamic_library_management.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dynamic_library_management.dao.implementation.ReportsDaoImplementation;

@WebServlet("/activeIssuedRecords")
public class ActiveIssuedRecordsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			List<String[]> activeIssues = new ArrayList<>();
			List<List<String>> raw = new ReportsDaoImplementation().getActiveIssuedBooks();
			for (List<String> row : raw) {
			    activeIssues.add(row.toArray(new String[0]));
			}
					
			request.setAttribute("activeIssues", activeIssues);

			RequestDispatcher rd = request.getRequestDispatcher("jsp/activeIssuedRecords.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
