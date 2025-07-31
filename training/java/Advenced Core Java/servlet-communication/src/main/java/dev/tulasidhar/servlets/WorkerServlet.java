package dev.tulasidhar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/WorkerServlet")
public class WorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Got the request from lazy servlet .Now I'm doing the work");
		
		
		
		response.getWriter().write("<h1>You have asked lazy servlet for a response ,"
								+ "Under the hood the worker servlet is giving you the response</h1> </div>");
		
		request.getRequestDispatcher("AnotherWorkerServlet").include(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
