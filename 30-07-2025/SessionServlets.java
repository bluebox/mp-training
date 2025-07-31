package servletSamples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginSession")
public class SessionServlets extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session=req.getSession(false);
		
		session.setAttribute("username", "karuna");
		PrintWriter out=resp.getWriter();
		
		out.println("sessionID: "+session.getId());
		out.println("user: "+session.getAttribute("username"));
		
		
		
	}
	
	

}
