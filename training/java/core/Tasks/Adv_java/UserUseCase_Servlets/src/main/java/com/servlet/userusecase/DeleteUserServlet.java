package com.servlet.userusecase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    
    public DeleteUserServlet() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
        	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root","Ashok@99122");
        	
        }
        catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public boolean isExists(String email) {
		String query = "SELECT name,email FROM users WHERE email=?";
		try {
			PreparedStatement stmt=conn.prepareStatement(query);
			stmt.setString(1, email);
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
		String email=req.getParameter("email");
		if(isExists(email)) {
			try {
				PreparedStatement stmt=conn.prepareStatement("DELETE FROM users WHERE email=?");
				stmt.setString(1, email);
				int rowsAffected=stmt.executeUpdate();
				res.setContentType("text/html");
				PrintWriter out=res.getWriter();
				if(rowsAffected!=0) {
					ResultSet rs=conn.prepareStatement("SELECT name, email FROM users").executeQuery();
					out.println("<html>");
					out.println("<body>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<th>Name</th>");
					out.println("<th>Email</th>");
					out.println("</tr>");
					while(rs.next()) {
						out.println("<tr>");
						out.println("<td>"+rs.getString(1)+"</td>");
						out.println("<td>"+rs.getString(2)+"</td>");
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
		else {
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			out.println("Member doesn't exists!");
		}
	}

}
