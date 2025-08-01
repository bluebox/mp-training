package com.session.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
	private static final String name = "UserCount";

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		ServletContext context = se.getSession().getServletContext();
		Integer users = (Integer) context.getAttribute(name);

		if (users == null) {
			users = 0;
		}

		context.setAttribute(name, ++users);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Integer UserCount;

		ServletContext context = se.getSession().getServletContext();

		UserCount = (Integer) context.getAttribute(name) - 1;

		context.setAttribute(name, UserCount);
	}

}
