package com.casestudy.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casestudy.dao.BooksDao;
import com.casestudy.domain.Availability;
import com.casestudy.domain.Book;
import com.casestudy.domain.Status;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("from do post of addbookservlet");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String availability = request.getParameter("availability");

        Book book = new Book(title, author, category, Status.fromCode(status), Availability.fromCode(availability));

        try {
            if(new BooksDao().createBook(book)) {
            	response.getWriter().println("success adding book.");
            }
            else {
            	response.getWriter().println("Error adding book.");
            }
            
//            response.sendRedirect("view_books.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error adding book.");
        }
    }


}
