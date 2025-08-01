package tarun;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String query="update user set user_name=?,user_age=? where  user_id=?";
			Connection con=DButil.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
		
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age"));
			int id=Integer.parseInt(request.getParameter("id"));
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setInt(3, id);
			int rs=ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			if(rs>0)
				pw.println("<h1>User updated successfully</h1>");
			else
				pw.println("<h1>no user foound with this id</h1>");
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
