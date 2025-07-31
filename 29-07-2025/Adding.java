import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet("/Adding")
public class Adding extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("Text/html");
		
		int num1=Integer.parseInt(req.getParameter("number1"));
		int num2=Integer.parseInt(req.getParameter("number2"));
		PrintWriter out=resp.getWriter();
		out.println("result: "+(num1+num2));
	}

	@Override
	public void destroy() {
		
	}


	
	
}
