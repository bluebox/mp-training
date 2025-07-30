

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
@WebFilter("/MyGenericFilter")
public class MyGenericFilter extends HttpServlet implements Filter {
       
	private FilterConfig filterConfig;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request,response); 
	}
	public void init(final FilterConfig filterConfig) {        
	    this.filterConfig = filterConfig;
	  } 

	  public void destroy() {                             
	  }

}
