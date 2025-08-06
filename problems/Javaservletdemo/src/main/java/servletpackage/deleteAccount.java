package servletpackage;

import jakarta.servlet.ServletException;
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
 * Servlet implementation class AddAccount
 */
@WebServlet("/delete")
public class deleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	PreparedStatement prepstmt;
	public void init() throws ServletException
	{
        try {
        	
            String jdbcDriver =getServletContext().getInitParameter("jdbcDriver");
            String dbUrl = getServletContext().getInitParameter("dbUrl");
            String dbUser = getServletContext().getInitParameter("dbUser");
            String dbPassword = getServletContext().getInitParameter("dbPassword");
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String Addquery="Delete from accounts.bank where Accno=? and Accountname=?;";
           prepstmt=connection.prepareStatement(Addquery);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }
    } 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			prepstmt.setInt(1,Integer.parseInt(request.getParameter("Accountno")));
			   prepstmt.setString(2,request.getParameter("username"));
			 
			   
			int num= prepstmt.executeUpdate();
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			if(num>0) {
				out.println("successfully removed the  account");
			}
			   
			   
		   } catch (NumberFormatException | SQLException e ) {
			e.printStackTrace();
			response.getWriter().println("Error while Deleting the Entry");
			
		   }
	}
	
	public void destroy() {
		try {
			connection.close();
			prepstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
