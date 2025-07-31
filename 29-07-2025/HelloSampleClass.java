import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/HelloSampleClass")
public class HelloSampleClass extends HttpServlet {
	
	private String message;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		message="Hello, My First dynamic Web page";
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PrintWriter out=resp.getWriter();
		
		out.println("<h1>" +message+ " </h1>");
	}

	@Override
	public void destroy() {
		
	}
	
	
	
}
