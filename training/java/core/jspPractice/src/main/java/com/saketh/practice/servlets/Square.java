package com.saketh.practice.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class Square extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Square() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pr=response.getWriter();
//		pr.print((int) request.getAttribute("sum")*10000);
		int a=Integer.parseInt(request.getParameter("sum"));
		pr.print("<h1>Square of "+a+" is "+(a*a)+"</h1>");
		HttpSession session=request.getSession();
		Cookie cookies[]=request.getCookies();
		String msg = null;
		for(Cookie c:cookies) {
			if(c.getName().equals("msg")) {
				msg=c.getValue();
				break;
				
			}
		}
		ServletContext ctx=request.getServletContext();
		pr.print("<h1>The message from session attribute is " +session.getAttribute("message")+" </h1>");
		pr.print("<h1>This is message from cookie"+msg+"</h1>");
		pr.print("<h1>"+ctx.getInitParameter("name")+"</h1>");
		ServletConfig config=getServletConfig();
		pr.print("<h1>"+config.getInitParameter("name")+"</h1>");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
