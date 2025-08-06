package com.servlet.userusecase;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) {
		String name=req.getParameter("nameField");
		String email=req.getParameter("emailField");
	}

}
