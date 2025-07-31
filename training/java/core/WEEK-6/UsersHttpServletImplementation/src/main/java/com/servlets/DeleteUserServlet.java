package com.servlets;

import jakarta.servlet.ServletConfig;
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

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet(
	    urlPatterns = "/deleteUserResponse",
	    initParams = {
	        @WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/user"),
	        @WebInitParam(name = "dbUser", value = "root"),
	        @WebInitParam(name = "dbPassword", value = "LiveClass@1603526") // Replace with your actual DB password
	    }
	)
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
    private PreparedStatement psDelete;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
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
            psDelete=connection.prepareStatement("delete from user.users where id=?");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection failed", e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

        try(PrintWriter writer = response.getWriter();){
            String idInput=request.getParameter("id");
            if(idInput.strip().length()==0) {
            	writer.println("Id must not be empty...");
            }
            else if(Integer.parseInt(idInput)<0) {
            	writer.println("Id cannot be less than 0...");
            }
            else {
            	int id=Integer.parseInt(idInput);
            	psDelete.setInt(1, id);
            	int count=psDelete.executeUpdate();
            	if(count==0) {
            		writer.println("User Not Found...");
            	}
            	else if(count>0) {
            		writer.println("User Deleted from Database Succesfully...");
            	}
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
