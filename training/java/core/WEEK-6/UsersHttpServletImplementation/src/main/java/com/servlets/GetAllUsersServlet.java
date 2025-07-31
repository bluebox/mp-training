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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class GetAllUsersServlet
 */
@WebServlet(
	    urlPatterns = "/getAllUsersResponse",
	    initParams = {
	        @WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost:3306/user"),
	        @WebInitParam(name = "dbUser", value = "root"),
	        @WebInitParam(name = "dbPassword", value = "LiveClass@1603526") // Replace with your actual DB password
	    }
	)
public class GetAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
    private Statement stmt;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
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
            stmt=connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection failed", e);
        }
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter writer = response.getWriter();) {
			ResultSet resultSet=stmt.executeQuery("select * from user.users");
			writer.println("<pre>id"+"		"+"name"+"			"+"gmail"+"			"+"age</pre>");
			while(resultSet.next()) {
				writer.println("<pre>"+resultSet.getInt(1)+"		"+resultSet.getString(2)+"			"+resultSet.getString(3)+"			"+resultSet.getInt(4)+"</pre>");
			}
			writer.println("<a href='index.html'>Go To Home</a>");
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
