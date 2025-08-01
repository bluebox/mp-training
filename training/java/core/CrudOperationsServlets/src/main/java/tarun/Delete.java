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


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query="delete from user where user_id=?";
		try {
			Connection con=DButil.getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			PrintWriter pw=response.getWriter();
			int id=Integer.parseInt(request.getParameter("id"));
			ps.setInt(1, id);
			int rs=ps.executeUpdate();
			if(rs>0)
				pw.println("<h1>user deleted successfully<h1>");
			else
				pw.println("<h1>no user find with this id<h1>");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	

}
