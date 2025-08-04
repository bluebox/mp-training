package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Service.MemberService;
import domain.Member;



/**
 * Servlet implementation class ViewAllMembersController
 */
@WebServlet("/ViewAllMembersController")
public class ViewAllMembersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private MemberService Service = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Member> members = Service.getAllMembers();
			System.out.println(members);
			System.out.println(members.size());
			request.setAttribute("membersList", members);
			request.getRequestDispatcher("/WEB-INF/views/viewAllMembers.jsp").forward(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to load members.");
			request.getRequestDispatcher("/WEB-INF/views/viewAllMembers.jsp").forward(request, response);
		}
	}



}
