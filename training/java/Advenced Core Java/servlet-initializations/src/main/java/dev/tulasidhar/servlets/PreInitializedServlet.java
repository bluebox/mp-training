package dev.tulasidhar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PreInitializedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void init() {
		System.out.println("This should happen right when application is loaded in to server");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("<h1>This servlet is initialized on server load</h1>");
		
	}

	

}
