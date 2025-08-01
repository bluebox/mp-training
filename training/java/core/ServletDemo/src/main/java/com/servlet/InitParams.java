package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InitParams extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This class is used to show the use of init parameters.
	 * @author W3schools360
	 */
//	
//	        //no-argument constructor
//	        public InitParamExample() {
//	       
//	        }

	      protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	    response.setContentType("text/html"); 
	        PrintWriter out = response.getWriter();
	      
	        ServletConfig config=getServletConfig(); 
	       
	        String appUser = config.getInitParameter("appUser");
	        
	        out.print("Application User: " + appUser + "");
	        
	        out.close();
	     }
	}

