package com.servlet.userusecase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddProducts extends GenericServlet{
	private static final long serialVersionUID = 1L;
	private Connection conn;
	public void init(ServletConfig config) {
		ServletContext cxt =config.getServletContext();
		String url=cxt.getInitParameter("url");
		String root=cxt.getInitParameter("root");
		String password=cxt.getInitParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		conn=DriverManager.getConnection(url,root,password);
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    	catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExists(String name) {
		String query = "SELECT name FROM products WHERE name=?";
		try {
			PreparedStatement stmt=conn.prepareStatement(query);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("nameField");
		String price = req.getParameter("priceField");
		if(!(name.trim().isEmpty()) || !(name==null) || !(price==null)) {
			double productPrice=Double.parseDouble(price);
			String query = "INSERT INTO products(name,price) VALUES (?,?)";
			if(!isExists(name)) {
				try(PreparedStatement stmt=conn.prepareStatement(query);){
					stmt.setString(1, name);
					stmt.setDouble(2, productPrice);
					int rowsAffected=stmt.executeUpdate();
					res.setContentType("text/html");
					PrintWriter out=res.getWriter();
					if(rowsAffected!=0) {
						ResultSet rs=conn.prepareStatement("SELECT id, name, price  FROM products").executeQuery();
						out.println("<html>");
						out.println("<body>");
						out.println("<table>");
						out.println("<tr>");
						out.println("<th>ProductId</th>");
						out.println("<th>ProductName</th>");
						out.println("<th>ProductPrice</th>");
						out.println("</tr>");
						while(rs.next()) {
							out.println("<tr>");
							out.println("<td>"+rs.getInt(1)+"</td>");
							out.println("<td>"+rs.getString(2)+"</td>");
							out.println("<td>"+rs.getDouble(3)+"</td>");
							out.println("</tr>");
						}
						out.println("</table>");
						out.println("</body>");
						out.println("</html>");
					}
					else {
						out.println("Data is Not inserted Properly");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
