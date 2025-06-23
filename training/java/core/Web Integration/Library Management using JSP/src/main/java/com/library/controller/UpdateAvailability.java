package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.DBConnection;

@WebServlet("/UpdateAvailability")
public class UpdateAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		PrintWriter out=response.getWriter();
		try {
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("update books set Availability=? where BookId=?");
			PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("select Availability from books where BookId=?");
			ps1.setLong(1, bookId);
			ResultSet res = ps1.executeQuery();
			String x="";
			if(res.next()) {
				x=res.getString(1);
				ps.setString(1, x.equals("A")?"I":"A");
				ps.setInt(2, bookId);
				if(ps.executeUpdate()>0) {
					response.sendRedirect("http://localhost:8080/Library_Management_using_JSP/ShowBooks");
				}
				else {
					out.print("<h1>Not updated</h1>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
