package dev.tulasidhar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LazyInitializedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public void init() {
    	System.out.println("This servlet is lazily initialized , so this is only occurs when the servlet is called");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("<h1>This servlet is initialized upon calling the servlet</h1>");
	}

	

}
