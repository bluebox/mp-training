package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteUser  extends HttpServlet{
	Connection con;
    PreparedStatement pstmt;

    public void init() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets", "root", "Gopi@2507");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
       
        PrintWriter out = res.getWriter();

        try {
            String username = req.getParameter("user");

            String sql = "DELETE FROM users WHERE username = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);

           pstmt.executeUpdate();

                out.println("User deleted successfully: " + username );
           

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error occurred: " + e.getMessage() + "</h3>");
        }
    }

    public void destroy() {
        try {
             pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
