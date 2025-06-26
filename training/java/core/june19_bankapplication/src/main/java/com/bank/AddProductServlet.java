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

//@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Statement st = con.createStatement();
            int res = st.executeUpdate(
                "INSERT INTO products VALUES('" + id + "', '" + name + "', '" + description + "', '" + price + "')");

            if (res > 0) {
                out.print("<h1>Product Added Successfully</h1>");
            } else {
                out.print("<h1>Failed to Add Product</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h1>Error occurred while adding product.</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h2>This servlet only supports POST method. Please use the form to submit data.</h2>");
    }
}