package com.weblisteners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	private static final String name="activeUserCount";

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
        Integer onlineUsers = (Integer) context.getAttribute(name);
        if (onlineUsers == null) {
            onlineUsers = 0;
        }
        context.setAttribute(name, ++onlineUsers);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Integer activeUserCount;
		ServletContext context=se.getSession().getServletContext();
		activeUserCount=(Integer)context.getAttribute(name)-1;
		context.setAttribute(name, activeUserCount);
	}
	
}
