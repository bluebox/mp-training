package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.BookService;

@WebServlet("/ShowBooks")
public class ShowBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet res = BookService.showBooks();
		PrintWriter out = response.getWriter();
		out.print("""
				<table>
					<tr>
						<th>Book ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>Category</th>
						<th>Status</th>
						<th>Availabilty</th>
					</tr>
				""");
		try {
			while (res.next()) {
				try {
					String s = "http://localhost:8080/Library_Management_using_JSP/addBook.html?mode=update&bookId="
							+ res.getInt(1) + "&title=" + res.getString(2) + "&author=" + res.getString(3)
							+ "&category=" + res.getString(4)+"&status="+res.getString(5);
					s = s.replaceAll(" ", "%20");
					out.print("<tr><td>" + res.getInt(1) + "</td><td>" + res.getString(2) + "</td><td>"
							+ res.getString(3) + "</td><td>" + res.getString(4) + "</td><td>" + res.getString(5)
							+ "</td><td>" + res.getString(6) + "</td>"
							+ "<td><a href='http://localhost:8080/Library_Management_using_JSP/UpdateAvailability?bookId="
							+ res.getString(1) + "'><button>Availability</button></a></td>" + "<td><a href=" + s
							+ "><Button>Update</Button></a></tr>");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print("</table>");
	}

}
