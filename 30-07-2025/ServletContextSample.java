package servletSamples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletContext")
public class ServletContextSample extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter output=resp.getWriter();
		
		ServletContext context=getServletContext();
		String context_value=context.getInitParameter("servletSample");
		
		output.println("<h1><center>"+context_value+"</center></h1>");
		
	}
	
	

}
