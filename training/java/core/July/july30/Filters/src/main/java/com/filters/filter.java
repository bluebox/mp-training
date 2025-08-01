package com.filters;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

@WebFilter("/*")
public class filter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		writer.println("this is in do Filter first");
		chain.doFilter(request, response);
		writer.println("this is in do Filter second");
	}

}
