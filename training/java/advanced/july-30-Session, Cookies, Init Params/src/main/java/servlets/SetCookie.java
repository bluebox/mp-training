package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetCookie")
public class SetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Set Cookies</title></head>");
		out.println("<body>");
		out.println("<h1>Set Your Personal Info and Preferences</h1>");
		out.println("<form action='SetCookie' method='post'>");
		out.println("<label for='yourName'>Your Name:</label>");
		out.println("<input type='text' id='yourName' name='yourName' value=''><br><br>");
		out.println("<label for='yourAge'>Your Age:</label>");
		out.println("<input type='number' id='yourAge' name='yourAge' value=''><br><br>");
		out.println("<input type='submit' value='Set Cookies'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("yourName");
		String age = request.getParameter("yourAge");

		Cookie nameCookie = new Cookie("myName", name);
		nameCookie.setMaxAge(60 * 60 * 24);
		nameCookie.setPath("/");
		response.addCookie(nameCookie);

		Cookie ageCookie = new Cookie("myAge", age);
		ageCookie.setMaxAge(60 * 60 * 24);
		ageCookie.setPath("/");
		response.addCookie(ageCookie);

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Cookies Set</title></head>");
		out.println("<body>");
		out.println("<h2>Cookies Set Successfully!</h2>");
		out.println("<p>Name: " + name + "</p>");
		out.println("<p>Age: " + age + "</p>");
		out.println("</body>");
		out.println("</html>");
	}
}
