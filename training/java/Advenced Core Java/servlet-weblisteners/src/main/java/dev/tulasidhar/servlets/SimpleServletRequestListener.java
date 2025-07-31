package dev.tulasidhar.servlets;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class SimpleServletRequestListener  implements ServletRequestListener {
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("Request Detected");
		System.out.println("Request from "+sre.getServletRequest().getRemoteAddr());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequestListener.super.requestDestroyed(sre);
	}
}