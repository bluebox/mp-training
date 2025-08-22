

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/SourceServlet")
public class SourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("Session Id :"+session.getId());
		out.print("<br/>");
		Cookie[] cookie = request.getCookies();
		for(int i=0;i<cookie.length;i++) {
		 out.print( cookie[i].getName());
		 out.print("<br/>");
		 out.print( cookie[i].getValue());
		}
		
		Cookie c = new Cookie("name", "1234");
		response.addCookie(c);
		
		Cookie[] cookie1 = request.getCookies();
		for(int i=0;i<cookie1.length;i++) {
		 out.print( cookie1[i].getName());
		 out.print("<br/>");
		 out.print( cookie1[i].getValue());
		}
		
		
	}
}
