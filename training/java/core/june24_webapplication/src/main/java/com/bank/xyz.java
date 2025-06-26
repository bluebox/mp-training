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

/**
 * Servlet implementation class xyz
 */
@WebServlet("/xyz")
public class xyz extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Optional for newer JDBC
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "bankDB", "Elect!ons123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int newPrice = Integer.parseInt(request.getParameter("price"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String sql = "UPDATE products SET price = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, newPrice);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<h1>Product price updated successfully.</h1>");
            } else {
                out.println("<h1>No product found with the given ID.</h1>");
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Error updating product price.</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show a message or redirect - this servlet only supports POST
        response.setContentType("text/html");
        response.getWriter().println("<h2>This servlet only supports POST method. Please use the form to submit data.</h2>");
    }
}
