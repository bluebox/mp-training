

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;

@WebFilter("/HelloWorldFilter")
public class HelloWorldFilter extends MyGenericFilter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Entering Filter");
	    request.setAttribute("hello","Hello World!");
	    chain.doFilter(request,response);
	    System.out.println("Exiting HelloWorldFilter"); 
	}
}
