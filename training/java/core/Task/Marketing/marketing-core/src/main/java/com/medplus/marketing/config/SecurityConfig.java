package com.medplus.marketing.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig implements ServletContextInitializer {
	
	@Value("${com.medplus.cas.service.protocal}")
	String protocol;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		servletContext.getSessionCookieConfig().setHttpOnly(true);
		if("https".equals(protocol))
			servletContext.getSessionCookieConfig().setSecure(true);
	}
	 
	
}