package InterServletCommunication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class sqrtServlet
 */
@WebServlet("/sqrt")
public class sqrtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
//		int k =  (int) request.getAttribute("sum");
		
//		int k = Integer.parseInt(request.getParameter("sum"));
		
//		HttpSession session = request.getSession();
//		int k = (int) session.getAttribute("sum");
		
		int k = 0;
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equals("sum")) {
				k=Integer.parseInt(c.getValue());
				System.out.println(k);
			}
		}
		
		writer.println("Sum is " + k);
		writer.println("Square of sum is " + k*k);
	}

}
