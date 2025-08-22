import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RequestDispatcher = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));

		PrintWriter out = response.getWriter();
		AveCalculator c = new AveCalculator();
		int result = c.claculator(num1, num2);
		request.setAttribute("result", result);
	    RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request,response);
		//out.print("Result is :"+result);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
