package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection con;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB", "root", "Elect!ons123");
            System.out.println(" DB connection established.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("helloo  ---------------------------");
            throw new ServletException(" DB connection failed", e); // Important
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accNo = Integer.parseInt(request.getParameter("accno"));
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        int balance = Integer.parseInt(request.getParameter("balance"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("taken in to account");

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO usertable (account_no, firstName, lastName, balance) VALUES (?, ?, ?, ?)");
            ps.setInt(1, accNo);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setInt(4, balance);

            int res = ps.executeUpdate();

            if (res > 0) {
                out.print("<h1>User Added Successfully</h1>");
            } else {
                out.print("<h1>Failed to Add User</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
