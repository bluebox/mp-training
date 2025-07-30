

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String username = request.getParameter("userName");  
        String password = request.getParameter("userPass");  
        if(password.equals("servlet")) {  
            RequestDispatcher rd = request.getRequestDispatcher("servlet2");  
            rd.forward(request, response);  
        } else {  
            out.print("Sorry, username or password error!");  
            RequestDispatcher rd = request.getRequestDispatcher("/index.html");  
            rd.include(request, response);  
        }  
 
	}

}
