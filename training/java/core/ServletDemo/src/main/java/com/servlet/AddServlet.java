package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
   {
	   int i=Integer.parseInt(req.getParameter("num1"));
	   int j=Integer.parseInt(req.getParameter("num2"));
	   int k=i+j;
	   PrintWriter out=res.getWriter();
	   out.println("result is :"+k);
   }

}
