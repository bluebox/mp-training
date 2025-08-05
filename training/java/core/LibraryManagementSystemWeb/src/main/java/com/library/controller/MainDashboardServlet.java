package com.library.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.impl.IssueRecordDaoImplementation;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;

@WebServlet("/MainServlet")
public class MainDashboardServlet extends HttpServlet {
    public MainDashboardServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String value=request.getParameter("clickedButton");
//		System.out.println(value);
		
		if(value.equalsIgnoreCase("addBook")) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			request.getRequestDispatcher("AddBookForm.jsp").forward(request, response);
		}
		else if (value.equalsIgnoreCase("viewBooks")) {
		    request.getRequestDispatcher("ViewBooksServlet").forward(request, response);
		}

		else if(value.equalsIgnoreCase("addMember")) {
		    request.getRequestDispatcher("AddMemberForm.jsp").forward(request, response);
		}

		else if(value.equalsIgnoreCase("viewMembers")) {
		    response.sendRedirect("viewMembers");  
		}

		else if (value.equalsIgnoreCase("issueBook")) {
		    System.out.println("clicked issue Book");

		    List<Member> members = null;
			try {
				members = new MemberServiceImplementation().fetchAllMembers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		    List<Book> books = null;
			try {
				books = new BookServiceImplementation().getAvailableBooks();
			} catch (Exception e) {
				e.printStackTrace();
			} 

		    request.setAttribute("members", members);
		    request.setAttribute("books", books);

		    request.getRequestDispatcher("issueBook.jsp").forward(request, response);
		}


		else if (value.equalsIgnoreCase("returnBook")) {
		    System.out.println("clicked return Book");

		    List<IssueRecord> issuedRecords = null;
			try {
				issuedRecords = new IssueRecordDaoImplementation().getAllIssuedRecords();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    request.setAttribute("issuedRecords", issuedRecords);
		    request.getRequestDispatcher("returnBook.jsp").forward(request, response);
		}



		else if(value.equalsIgnoreCase("reports")) {
//			System.out.println("clicked on Reports");
			 response.sendRedirect("reports");
		}
	}

}

//
//@WebServlet("/addMemberServlet")
// class addMember extends HttpServlet {
//    
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		request.getRequestDispatcher("AddMemberForm.jsp").forward(request, response);
//	}
//
//}