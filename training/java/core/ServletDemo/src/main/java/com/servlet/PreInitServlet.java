package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class PreInitServlet extends HttpServlet {

    
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
        super.init();
        System.out.println("PreInitServlet example.");
    }
}