package com.form;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        String phno = req.getParameter("phno");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "Practice", "Vbhanu@2003")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "DELETE FROM form WHERE phno = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, phno);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    res.sendRedirect("/Demo1/Show");
                } else {
                    System.out.print("<h1>Record not found</h1>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(res.getWriter());
        }
    }
}