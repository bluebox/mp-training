package uday;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


public class Hello extends GenericServlet {
	private static final long serialVersionUID = 1L;
  
	
	public void service(ServletRequest req, ServletResponse res)
	        throws ServletException, IOException
	    {
	        res.setContentType("text/html");
	        PrintWriter pw = res.getWriter();
	        pw.println("<h2>Hello World</h2>");
	        System.out.println("in service");
	    }
}
