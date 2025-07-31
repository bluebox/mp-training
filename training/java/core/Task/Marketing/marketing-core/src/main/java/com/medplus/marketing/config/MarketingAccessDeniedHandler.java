package com.medplus.marketing.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MarketingAccessDeniedHandler implements AccessDeniedHandler {


	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		log.info("Enterd Custom Access Denied Handler.......!");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String ajaxHeader = request.getHeader("X-Requested-With");
		boolean isAnonymous = authenticationTrustResolver.isAnonymous(auth);
		if ("XMLHttpRequest".equals(ajaxHeader)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
			request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,
					isAnonymous ? "USER_NOT_LOGGED_IN" : "ACCESS_DENIED");
			dispatcher.forward(request, response);
			return;
		}

		log.info("Redirect to access denied page");
		if(!isAnonymous) {
			response.sendRedirect(request.getContextPath() + "/accessDenied");
			return;
		} 

		throw exception;
	}
}