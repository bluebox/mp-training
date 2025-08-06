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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class viewBalance
 */
//@WebServlet("/viewBalance")
public class viewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	PreparedStatement prepstmt;
	PreparedStatement prepstmt2;
	public void init() throws ServletException
	{
        try {
        	
            String jdbcDriver =getServletContext().getInitParameter("jdbcDriver");
            String dbUrl = getServletContext().getInitParameter("dbUrl");
            String dbUser = getServletContext().getInitParameter("dbUser");
            String dbPassword = getServletContext().getInitParameter("dbPassword");
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String Addquery="select * from accounts.bank;";
            String getbalance="select * from accounts.bank where Accno=? and Accountname=?;";
           prepstmt=connection.prepareStatement(Addquery);
           prepstmt2=connection.prepareStatement(getbalance);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			prepstmt2.setInt(1,Integer.parseInt(request.getParameter("Accountno")));
			 prepstmt2.setString(2,request.getParameter("username"));
			 
			 ResultSet num= prepstmt2.executeQuery();
			ResultSet rs= prepstmt.executeQuery();
				PrintWriter out=response.getWriter();
				response.setContentType("text/html");
				List<Accounts> accounts=new ArrayList<>();
				while(rs.next()) {
					accounts.add(new Accounts(rs.getInt("Accno"),rs.getString("Accountname"),rs.getDouble("Amount")));
				}
				out.println("<h1>the amount of Accno :</h1>"+num.getInt("Accno")+" is Rs."+num.getDouble("Amount"));
				out.println("Accno,Accountname,Amount");
				for(Accounts element:accounts) {
					out.println(element.toString());
				}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
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
