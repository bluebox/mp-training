

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addNumbers")
public class addNumbers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int a = (Integer)request.getAttribute("a");
		int b = (Integer)request.getAttribute("b");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(request.getParameter("number1") + " + ");
		out.print(request.getParameter("number2") + " = ");
		out.print(a+b);
	}

}
