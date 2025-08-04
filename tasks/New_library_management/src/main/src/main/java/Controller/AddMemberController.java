package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.MemberService;
import domain.Member;
import domain.checking_enum.Gender;



/**
 * Servlet implementation class AddMemberController
 */
@WebServlet("/AddMemberController")
public class AddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService service=new MemberService();   
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AddMember.jsp");
	        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String gender=request.getParameter("gender");
		String address = request.getParameter("address");
		
		
		Member member = new Member(name,email,Long.parseLong(mobile),address,Gender.getstatus(String.valueOf(gender.charAt(0))));
		
		try {
			service.addMember(member);
			request.setAttribute("message", "Added new member Successfully");
			request.getRequestDispatcher("/WEB-INF/views/AddMemberresult.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/AddMemberresult.jsp").forward(request, response);
		}
	    
	}

}
