package com.learning;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class SquareServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		//HttpSession session =req.getSession();
		//int i=(int)session.getAttribute("i");
		//int j=(int)session.getAttribute("j");
		int i=0;
		int j=0;
		for(Cookie c: cookies) { 
			if(c.getName().equals("num1")) {
				i=Integer.parseInt(c.getValue());
			} if(c.getName().equals("num2")) {
				j=Integer.parseInt(c.getValue());
				}
			}
		 
		res.setContentType("text/html");
		try {
			PrintWriter out = res.getWriter();
			out.println(i*j);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
