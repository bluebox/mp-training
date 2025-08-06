package com.learning;

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

@WebFilter("/add")
public class InputFilter extends HttpFilter implements Filter {
       
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpServletRequest req=(HttpServletRequest) request;
		int num1=Integer.parseInt(req.getParameter("num1"));
		int num2=Integer.parseInt(req.getParameter("num2"));
		if(num1>1 && num2>1) {
			chain.doFilter(request, response);
		}
		else {
			out.println("<h3>Entered an invalid input</h3>");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
