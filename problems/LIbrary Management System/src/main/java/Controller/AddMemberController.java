package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Domain.Gender;
import Domain.Member;
import Service.ServiceLayer;

/**
 * Servlet implementation class AddMemberController
 */
@WebServlet("/AddMemberController")
public class AddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServiceLayer service=new ServiceLayer();   
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String gender=request.getParameter("gender");
		String address = request.getParameter("address");
		
		
		Member member = new Member(0,name,email,mobile,Gender.getGender(gender.toUpperCase().substring(0,1)),address);
		
		try {
			service.registerMember(member);
			request.setAttribute("message", "Added new member Successfully");
			request.getRequestDispatcher("AddMember.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("AddMember.jsp").forward(request, response);
		}
	    
	}

}
