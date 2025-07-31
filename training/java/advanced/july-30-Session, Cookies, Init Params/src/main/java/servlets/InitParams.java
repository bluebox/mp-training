package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	    urlPatterns = "/InitParams",
	    initParams = {
	        @WebInitParam(name = "Greetings", value = "Hello"),
	        @WebInitParam(name = "Name", value = "Kaushik"),
	        @WebInitParam(name = "Age", value = "22")
	    }
	)
public class InitParams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitParams() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Enumeration<String> paramNames = getServletConfig().getInitParameterNames();

		if (!paramNames.hasMoreElements()) {
			if (out != null) {
				out.println("<p>No init parameters found for this servlet.</p>");
			} else {
				System.out.println("No init parameters found for this servlet.");
			}
			return;
		}

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = getServletConfig().getInitParameter(paramName);
			if (out != null) {
				out.println("<p><strong>" + paramName + ":</strong> " + paramValue + "</p>");
			} else {
				System.out.println(paramName + ": " + paramValue);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
