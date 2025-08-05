package com.saketh.practice.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Addition extends HttpServlet {
	private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int num1=Integer.parseInt(req.getParameter("num1"));
		int num2=Integer.parseInt(req.getParameter("num2"));

			PrintWriter pr=res.getWriter();
			pr.print("<h1>"+(num1+num2)+"</h1>");
		
    }
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		int num1=Integer.parseInt(req.getParameter("num1"));
		int num2=Integer.parseInt(req.getParameter("num2"));
		PrintWriter pr=res.getWriter();
		
//		req.setAttribute("sum",(num1+num2));
//		RequestDispatcher rs=req.getRequestDispatcher("Square");
//		rs.forward(req,res);
		HttpSession session = req.getSession();
		String value=URLEncoder.encode("This is the cookie message","UTF-8");
		session.setAttribute("message", "This is session message");
		Cookie cookie=new Cookie("msg",value);
		res.addCookie(cookie);
		res.sendRedirect("Square?sum="+(num1+num2));
	}
}
