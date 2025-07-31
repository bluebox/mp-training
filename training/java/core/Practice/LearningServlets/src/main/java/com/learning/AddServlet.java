package com.learning;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) {
		int i=Integer.parseInt(req.getParameter("num1").trim());
		int j=Integer.parseInt(req.getParameter("num2").trim());
		//HttpSession session =req.getSession();
		//session.setAttribute("i", i);
		//session.setAttribute("j", j);
		Cookie cookie1=new Cookie("num1", i+""); Cookie cookie2=new Cookie("num2",j+""); res.addCookie(cookie1); res.addCookie(cookie2);

		try {
			res.sendRedirect("square");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
