package com.dynamic_library_management.controllers;

import java.io.IOException;

import com.dynamic_library_management.dao.implementation.IssueRecordDaoImplementation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/finalReturn")
public class FinalReturnRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

//			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();

			int memberId = Integer.parseInt(request.getParameter("memberId"));
			int bookId = Integer.parseInt(request.getParameter("bookId"));

			String print_message = new IssueRecordDaoImplementation().returnBook(memberId, bookId);

			request.setAttribute("message", print_message);
			if (print_message.equals("Book returned successfully") ){
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "error");
			}

			RequestDispatcher rd = request.getRequestDispatcher("returnRecord");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}