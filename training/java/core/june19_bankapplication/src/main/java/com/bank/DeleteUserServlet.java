package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserServlet
 */
//@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Connection con;

	    public void init() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB", "root", "Elect!ons123");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int accNo = Integer.parseInt(request.getParameter("accno"));
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        try {
	            Statement st = con.createStatement();
	            int res = st.executeUpdate(
	            	    "DELETE FROM usertable WHERE accno = '" + accNo + "'");
	            if (res > 0) {
	                out.print("<h1>Deleted successfully</h1>");
	            } else {
	                out.print("<h1>User not found with given id</h1>");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.print("<h1>Error occurred while adding user.</h1>");
	        }
	    }

}