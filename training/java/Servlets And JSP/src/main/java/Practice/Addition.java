package Practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Addition
 */
@WebServlet("/add")
public class Addition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num1 = Integer.parseInt(request.getParameter("number1"));
		int num2 = Integer.parseInt(request.getParameter("number2"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Sum is " + (num1+num2));
		
		int sum = num1+num2;
		
//		response.sendRedirect("sqrt?sum="+sum); // URL Rewriting
		 
//		HttpSession session = request.getSession(); // Using session
//		session.setAttribute("sum", sum);
//		response.sendRedirect("sqrt");
		
//		Cookie cookie = new Cookie("sum",sum+""); // using cookie
//		response.addCookie(cookie);
//		response.sendRedirect("sqrt");
		
//		request.setAttribute("sum", sum);
//		RequestDispatcher rd = request.getRequestDispatcher("sqrt"); // using request dispatcher
//		rd.forward(request, response);
	}

}
