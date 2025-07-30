package HelloServlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddNums extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        int a=Integer.parseInt(request.getParameter("Num1"));
        int b=Integer.parseInt(request.getParameter("Num2"));
        
        response .getWriter().println("<h1>Sum of two numbers is:  </h1>" + (a+b));

        
//        response.getWriter().println("<h1>Hello from Servlet!</h1>");
    }
}