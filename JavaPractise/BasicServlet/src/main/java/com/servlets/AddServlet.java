package com.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		int k = i + j;
//		PrintWriter out = res.getWriter();
//		out.println("result is : "+k);
		req.setAttribute("k",k);
		rd = req.getRequestDispatcher("sq");
		rd.forward(req, res);
	}

}
