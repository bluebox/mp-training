package com.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.naming.factory.MailSessionFactory;

@WebServlet(
    urlPatterns = "/addUserResponse",
    initParams = {
        @WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/user"),
        @WebInitParam(name = "dbUser", value = "root"),
        @WebInitParam(name = "dbPassword", value = "LiveClass@1603526") // Replace with your actual DB password
    }
)
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection connection;
    private PreparedStatement psInsert;

    public AddUserServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Get initialization parameters
            String dbUrl = config.getInitParameter("dbUrl");
            String dbUser = config.getInitParameter("dbUser");
            String dbPassword = config.getInitParameter("dbPassword");

            // Establish database connection
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            psInsert=connection.prepareStatement("insert into user.users(name,gmail,age) values(?,?,?)");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try(PrintWriter writer = response.getWriter();){
        	String name=request.getParameter("name");
            String gmail=request.getParameter("gmail");
            String ageInput=request.getParameter("age");
            if(name.strip().length()==0 || gmail.strip().length()==0 || ageInput.strip().length()==0) {
            	writer.println("All the fields must be given...");
            }
            else if(name.length()>50 || gmail.length()>50) {
            	writer.println("Name and gmail cannot exceed 50 characters...");
            }
            else if(Integer.parseInt(ageInput)<=0 || Integer.parseInt(ageInput)>100) {
            	writer.println("Age must be greater than 0 and cannot exceed 100...");
            }
            else {
            	int age=Integer.parseInt(ageInput);
            	psInsert.setString(1, name);
            	psInsert.setString(2, gmail);
            	psInsert.setInt(3, age);
            	int count=psInsert.executeUpdate();
            	writer.println("User Added to Database Succesfully...");
            }
            writer.println("<br/><a href='index.html'>Go To Home</a>");
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
