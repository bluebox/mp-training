package com.dynamic_library_management.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import com.dynamic_library_management.dao.implementation.ReportsDaoImplementation;

@WebServlet("/categoryCount")
public class CategoryCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Map<Object, Long> categoryCountMap = new ReportsDaoImplementation().countOfBooksPerCategory();
		 
		 System.out.println(categoryCountMap);
		    
		    request.setAttribute("categoryCountMap", categoryCountMap);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/categoryCount.jsp");
		    dispatcher.forward(request, response);
	}

}
