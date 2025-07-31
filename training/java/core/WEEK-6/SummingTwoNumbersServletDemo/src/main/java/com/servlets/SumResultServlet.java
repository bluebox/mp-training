package com.servlets;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SumResultServlet
 */
@WebServlet("/SumResultServlet")
public class SumResultServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public SumResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		int firstNumber=Integer.parseInt(request.getParameter("firstNumber"));
		int secondNumber=Integer.parseInt(request.getParameter("secondNumber"));
		int sum=firstNumber+secondNumber;
		PrintWriter writer=response.getWriter();
		writer.println("Sum of Two Numbers is : "+sum);
	}

}
