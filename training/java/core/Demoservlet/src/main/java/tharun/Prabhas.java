package tharun;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/Prabhas")
public class Prabhas extends HttpServlet {

	    // service method
	    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    	int sum=0;
	    	sum=Integer.parseInt(req.getParameter("number1"))+ Integer.parseInt(req.getParameter("number2"));
	        PrintWriter pw = res.getWriter();
	        pw.println("<h1>The Result is</h1>");
	        pw.println("<span>"+sum+"</span>");
	        System.out.println("in service");
	    }

	 
	  

}