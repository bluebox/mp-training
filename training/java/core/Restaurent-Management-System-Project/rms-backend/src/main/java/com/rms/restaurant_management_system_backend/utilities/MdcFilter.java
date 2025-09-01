package com.rms.restaurant_management_system_backend.utilities;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MdcFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;

	public MdcFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String username = "ANONYMOUS";
			String authHeader = request.getHeader("Authorization");
			if (authHeader != null) {
				username = jwtUtil.extractUsername(authHeader.substring(7));
			}
			MDC.put("user", username);
			filterChain.doFilter(request, response);

		} finally {
			MDC.clear();
		}
	}
}