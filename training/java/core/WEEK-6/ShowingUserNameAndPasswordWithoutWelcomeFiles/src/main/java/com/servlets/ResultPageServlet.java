package com.servlets;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ResultPageServlet
 */
@WebServlet("/ResultPageServlet")
public class ResultPageServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public ResultPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		
		PrintWriter writer=response.getWriter();
		writer.println("User Name : "+userName);
		writer.println("User Password : "+userPassword);
	}

}
