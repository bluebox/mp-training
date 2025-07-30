

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidServlet
 */
@WebServlet("/ValidServlet")
public class ValidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletConfig cfg;
	public void init(ServletConfig config) throws ServletException {
		cfg = config;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un = request.getParameter("txtuser");
		String pw = request.getParameter("txtpass");
		boolean flag = false;
		Enumeration<String> initparams = cfg.getInitParameterNames();
		while(initparams.hasMoreElements())
		{
			String name = initparams.nextElement();
			String pass = cfg.getInitParameter(name);
			if(un.equals(name) && pw.equals(pass))
			{
				flag = true;
			}
		}
		if(flag)
		{
			response.getWriter().print("Valid user!");
		}
		else
		{
			response.getWriter().print("Invalid user!");
		}
		}
}
