package com.servletsExampl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class UserData extends HttpServlet {
    private Connection conn;
    private PreparedStatement st;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "Anand", "1925112816@Aa"
            );
            st = conn.prepareStatement("INSERT INTO servlets (name, age) VALUES (?, ?)");
            System.out.println("DBConnection is started");
        } catch (Exception e) {
            e.printStackTrace(); // Better than just printing message
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");

        try {
            int age = Integer.parseInt(ageStr);

            st.setString(1, name);
            st.setInt(2, age);

            int result = st.executeUpdate();

            if (result > 0) {
                out.println("<h2>User data inserted successfully!</h2>");
            } else {
                out.println("<h2>Failed to insert data.</h2>");
            }

        } catch (NumberFormatException e) {
            out.println("<h2>Invalid age entered.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Database error occurred.</h2>");
        }
    }

    @Override
    public void destroy() {
        try {
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
