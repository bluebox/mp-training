

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/session")
public class session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public session() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cookie firstName = new Cookie("first_name",
				 request.getParameter("first_name"));
		 Cookie lastName = new Cookie("last_name",
				 request.getParameter("last_name"));
		 firstName.setMaxAge(60*60*24);
	      lastName.setMaxAge(60*60*24);
	      response.addCookie( firstName );
	      response.addCookie( lastName );
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Setting Cookies Example";
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      
	      out.println(docType +
	         "<html>\n" +
	            "<head><title>" + title + "</title></head>\n"
	             +
	            
	            "<body bgcolor = \"#f0f0f0\">\n" +
	               "<h1 align = \"center\">" + title + "</h1>\n" +
	               "<ul>\n" +
	                  "  <li><b>First Name</b>: "
	                  + request.getParameter("first_name") + "\n" +
	                  "  <li><b>Last Name</b>: "
	                  + request.getParameter("last_name") + "\n" +
	               "</ul>\n" +
	            "</body></html>"
	         
	      );
	   
	
	
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		String docType =  "<!doctype html public \"-//w3c//dtd html 4.0 " +
//		         "transitional//en\">\n";
//
//		out.println(docType +  "<html>\n" +
////		         "<head><title>" + title + "</title></head>\n" +
//		         "<body bgcolor = \"#f0f0f0\">\n" );
		if (cookies != null) {
			out.println("<h2> Found cookies name and value<h2>");
			for (int i = 0;i< cookies.length; i++) {
				cookie = cookies[i];
				out.println("Name : " +cookie.getName() + ", ");
				out.println("value : " +cookie.getValue() + ", ");
				
				
			}
		}else {
			out.println("<h2> No cookies found</h2>");
			
		}
		
	    out.println("</body>");
		out.println("</html>");
	}
}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}

//}
