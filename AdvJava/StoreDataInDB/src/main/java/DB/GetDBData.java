package DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetDBData")
public class GetDBData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				out.println("DB Connected<br>");
			}
			
			String query = "Select * from userdata";
			
			out.println("<h3>Email  Password</h3>");
			
			Statement st =conn.createStatement();
			
			ResultSet rs= st.executeQuery(query);
			
			while(rs.next()) {
				out.println("<tr>"+ "<th>"+rs.getString(1)+"</th>"+" "+ "<th>"+rs.getString(2)+"</th>"+"</tr><br>");
			}

			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
