package servletSamples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie cookie=new Cookie("udemy","12567");
		
		resp.addCookie(cookie);
		
		Cookie[] cookies=req.getCookies();
		
		for(int i=0;i<cookies.length;i++) {
			resp.getWriter().println("name: "+cookies[i].getName());
			resp.getWriter().println("value: "+cookies[i].getValue());
		}
	}
	
	

}
