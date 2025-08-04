package library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.model.Member;
import library.service.MemberServiceImpl;
import library.service.interfaces.MemberService;

@WebServlet("/viewMembers")
public class ViewMembersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberService memberService;
    
    public ViewMembersServlet() {
        super();
        this.memberService = new MemberServiceImpl();

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Member> membersList = memberService.findMembers(new Member());
            request.setAttribute("membersList", membersList);
            
            String statusMessage = (String) request.getSession().getAttribute("statusMessage");
            String statusType = (String) request.getSession().getAttribute("statusType");
            if (statusMessage != null) {
                request.setAttribute("statusMessage", statusMessage);
                request.setAttribute("statusType", statusType);
                request.getSession().removeAttribute("statusMessage");
                request.getSession().removeAttribute("statusType");
            }
            
            request.getRequestDispatcher("/viewMembers.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while retrieving members: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}