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
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService service=new MemberService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/updateMember.jsp");
	        dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id")); // assuming you're passing ID
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");

		Member member=new Member(id,name,email,Long.parseLong(mobile),address,Gender.getstatus(String.valueOf(gender.charAt(0))));

		try{
			boolean updatemember= service.updateMember(member,id);
			if(updatemember) {
				request.setAttribute("message", "Updated member SuccessFully");
				request.getRequestDispatcher("/WEB-INF/views/UpdateMemberResult.jsp").forward(request, response);
			}
		} catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/UpdateMemberResult.jsp").forward(request, response);
		}
	}

}
