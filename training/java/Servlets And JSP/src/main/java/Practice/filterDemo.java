package Practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class filterDemo
 */
@WebFilter("/add")
public class filterDemo extends HttpFilter implements Filter {
       

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// pass the request along the filter chain
		//chain.doFilter(request, response);
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpServletRequest req=(HttpServletRequest) request;
		int num1=Integer.parseInt(req.getParameter("number1"));
		int num2=Integer.parseInt(req.getParameter("number2"));
		if(num1>1 && num2>1) {
			chain.doFilter(request, response);
		}
		else {
			out.println("<h3>Entered an invalid input</h3>");
		}
	}

}
