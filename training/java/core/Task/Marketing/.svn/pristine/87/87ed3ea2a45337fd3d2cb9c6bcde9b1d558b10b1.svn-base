package com.medplus.marketing.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSessionTimeoutFilter  extends GenericFilterBean{
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch(AccessDeniedException e) {
			String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(ajaxHeader)) {
				boolean isAnonymous = authenticationTrustResolver
						.isAnonymous(SecurityContextHolder.getContext().getAuthentication());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
				request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,
						isAnonymous ? "USER_NOT_LOGGED_IN" : "ACCESS_DENIED");
				dispatcher.forward(request, response);
				return;
			}
			log.info("Redirect to login page");
			throw e;
		}
	}

}
