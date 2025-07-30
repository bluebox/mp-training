package DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteDBData
 */
@WebServlet("/DeleteDBData")
public class DeleteDBData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email=request.getParameter("email");
		
		String url = "jdbc:mysql://localhost:3306/users";
		
		PrintWriter  out = response.getWriter();
        response.setContentType("text/html");

		
        try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(url, "devuser", "211Fa@4223");
			if(conn!=null) {
				System.out.println("DB Connected");
				out.println("DB Connected");

			}
			String query ="Delete from userdata where Email=?";
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, email);
			
			int value=pst.executeUpdate();
			
			if(value>0) {
				out.println("<h1>Data Deleted from DB</h1>");
				out.println("<a href='GetDBData'>Get Data From DB</a>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
     
	}

}
