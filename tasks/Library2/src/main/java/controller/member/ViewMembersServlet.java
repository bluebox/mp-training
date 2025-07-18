package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Member;
import serviceimpl.MemberServiceImpl;

import java.io.IOException;
import java.util.List;


@WebServlet("/viewmembers")
public class ViewMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberServiceImpl memberService = new MemberServiceImpl();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
            List<Member> allMembers = memberService.getAllMembers();
            req.setAttribute("members", allMembers);
        } catch (Exception e) {
            req.setAttribute("error", "Unable to fetch members: " + e.getMessage());
        }
        req.getRequestDispatcher("/view/members/viewMembers.jsp").forward(req, resp);
	}


}
