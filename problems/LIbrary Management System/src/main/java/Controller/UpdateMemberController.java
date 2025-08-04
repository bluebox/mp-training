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
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServiceLayer service=new ServiceLayer();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id")); // assuming you're passing ID
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");

		Member member=new Member(id,name,email,mobile,Gender.getGender(gender.toUpperCase().substring(0,1)),address);

		try{
			Member updatemember= service.updateMember(member);
			if(updatemember != null) {
				request.setAttribute("message", "Updated member SuccessFully");
				request.getRequestDispatcher("updateMember.jsp").forward(request, response);
			}
		} catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("updateMember.jsp").forward(request, response);
		}
	}

}
