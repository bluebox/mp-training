package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


public class AdditionServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(request.getParameter("num1")!=null && request.getParameter("num1")!=null) {
			int n1=Integer.parseInt(request.getParameter("num1"));
			int n2=Integer.parseInt(request.getParameter("num2"));
			int n3=n1+n2;
			out.println("Sum is : "+n3);
		}
		else {
			out.println("elements are null ");
		}
	}

}
