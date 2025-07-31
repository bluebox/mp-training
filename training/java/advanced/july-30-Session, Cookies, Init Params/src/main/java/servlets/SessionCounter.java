package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionCounter")
public class SessionCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SessionCounter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		session.setMaxInactiveInterval(30 * 60);

		Integer visitCount = (Integer) session.getAttribute("visitCount");

		if (visitCount == null) {
			visitCount = 1;
		} else {
			visitCount++;
		}

		session.setAttribute("visitCount", visitCount);

		String sessionId = session.getId();

		out.println("<body>");
		out.println("Session count is " + visitCount + "<br/>");
		out.println("Session ID: " + sessionId + "<br/>"); 
		out.println("<form action='SessionCounter' method='post'>");
		out.println("<button type='submit'>Reset Session</button>");
		out.println("</form>");
		out.println("</body>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
			System.out.println("Session invalidated: " + session.getId());
		}

		response.sendRedirect(request.getContextPath() + "/SessionCounter");
	}
}