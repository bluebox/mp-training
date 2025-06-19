package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//@WebServlet("/Addition")
public class Addition extends HttpServlet {
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		int x=Integer.parseInt(request.getParameter("id1"));
		int y=Integer.parseInt(request.getParameter("id2"));
		PrintWriter out=response.getWriter();
		out.println("""
				<!DOCTYPE html>
				<html>
				<head>
				<meta charset="UTF-8">
				<title>Example</title>
				</head>
				<body>
					<p>Sum =</p>"""+(x+y)+"""
				</body>
				</html>
				""");
	}

}
