
import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloWorldServlet extends HttpServlet {
	
	
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} 
    	catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found");
		}
        System.out.println("Servlet initialized");
    }
    
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String place = request.getParameter("place");
        int age = Integer.parseInt(request.getParameter("age"));
        
        response.setContentType("text/html");
        response.getWriter().println("<h3>Received Data:</h3>");
        response.getWriter().println("Name: " + username + "<br>");
        response.getWriter().println("Email: " + email + "<br>");
        response.getWriter().println("Age: " + age + "<br>");
        response.getWriter().println("Place: " + place + "<br>");
    }
}