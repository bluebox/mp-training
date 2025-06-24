package uday;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class preinit
 */
@WebServlet("/preinit") 
public class preinit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
    public void init() {
    	System.out.println("Entered Init The First");
	}
	


}
