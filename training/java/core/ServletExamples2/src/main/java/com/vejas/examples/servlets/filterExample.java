package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class filterExample
 */
@WebFilter("/PreIntializationServlet")
public class filterExample extends HttpFilter implements Filter {

	private final String name="Vejas";
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		out.println("In the filter has access to the request parameters , checking the parameter and passing to the servlet: ");
		if(!request.getParameter("name").equals(name)) {
			out.println("the entered username is wrong");
			 HttpServletResponse httpResponse = (HttpServletResponse) response;
			 httpResponse.sendRedirect("index.html");
		}
		chain.doFilter(request, response);
		out.println("after the servlet ");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
