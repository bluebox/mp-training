
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorldServlet extends HttpServlet {
	
	
    private static final long serialVersionUID = 1L;

    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/learn";
    private static final String root = "devuser";
    private static final String password = "Medplus@123";
    
    @Override
    public void init() throws ServletException {
        
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,root,password);
			
		} 
    	catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found");
		} catch (SQLException e) {
			System.out.println("SQL exception ");
		}
        System.out.println("Servlet initialized");
    }
    
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String place = request.getParameter("place");
        int age = Integer.parseInt(request.getParameter("age"));
        
        try(PreparedStatement ps = connection.prepareStatement("insert into user(name,email,age,place) values(?,?,?,?);")){
        	ps.setString(1,username);
            ps.setString(2,email);
            ps.setInt(3,age);
            ps.setString(4,place);
            int rows = ps.executeUpdate();
            if(rows > 0) {
            	System.out.println("row inserted");
            }
        }
        catch(Exception e) {
//        	System.out.println("error in connection or sql stmt");
        	e.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().println("<h3>Received Data:</h3>");
        response.getWriter().println("Name: " + username + "<br>");
        response.getWriter().println("Email: " + email + "<br>");
        response.getWriter().println("Age: " + age + "<br>");
        response.getWriter().println("Place: " + place + "<br>");
    }
	
	@Override
	public void destroy() {
	    try {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	            System.out.println("Database connection closed.");
	        }
	        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
			System.out.println("MySQL Cleanup thread shutdown.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    super.destroy();
	}

}