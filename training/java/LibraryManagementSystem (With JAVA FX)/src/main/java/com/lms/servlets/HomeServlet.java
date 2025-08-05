package com.lms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Action")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String buttonSelected = request.getParameter("homeButton");
		request.setAttribute("fileToRender",buttonSelected+".jsp");
		request.getRequestDispatcher(buttonSelected+"Servlet").forward(request, response);
	}

}
