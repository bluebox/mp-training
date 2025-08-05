package com.LibraryManagement.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/MainPageController")
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MainPageController() {
        super();
      
    }

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String action=request.getParameter("action");
		if("addbook".equalsIgnoreCase(action)) {
		request.getRequestDispatcher("AddBookOptions.jsp").forward(request, response);
		}
		 if("addmember".equalsIgnoreCase(action))
		 {
				request.getRequestDispatcher("AddMemberOptions.jsp").forward(request, response);

		 }
		 if("issueandreturn".equalsIgnoreCase(action))
		 {
				request.getRequestDispatcher("IssueAndReturn.jsp").forward(request, response);

		 }
		 if("reports".equalsIgnoreCase(action))
		 {
				request.getRequestDispatcher("Reports.jsp").forward(request, response);

		 }
		
	}

}
