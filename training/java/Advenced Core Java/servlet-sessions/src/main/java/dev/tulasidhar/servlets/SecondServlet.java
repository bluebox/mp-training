package dev.tulasidhar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.getWriter().write("The name from the session:" +session.getAttribute("name"));
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			response.getWriter().write("\ncookie stored : "+cookie.getName() + " " + cookie.getValue());
		}
	}
}
