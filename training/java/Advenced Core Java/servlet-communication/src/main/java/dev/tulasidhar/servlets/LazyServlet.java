package dev.tulasidhar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LazyServlet")
public class LazyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LazyServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//just forwarding the request to the other servlet 
		
		//response.sendRedirect("home.html");
		request.getRequestDispatcher("WorkerServlet").forward(request, response);
		//request.getRequestDispatcher("home.html").forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
