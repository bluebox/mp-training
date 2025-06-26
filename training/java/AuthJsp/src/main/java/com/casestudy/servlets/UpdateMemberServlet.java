package com.casestudy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casestudy.dao.MembersDao;
import com.casestudy.domain.Gender;
import com.casestudy.domain.Member;


@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private MembersDao membersDao;

	    @Override
	    public void init() {
	        membersDao = new MembersDao();
	    }

    
    public UpdateMemberServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("Fetch Member".equals(action)) {
            try {
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                Member member = membersDao.getMemberById(memberId);
                if (member != null) {
                    request.setAttribute("member", member);
                } else {
                    request.setAttribute("message", "Member not found.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Member ID format.");
            }
        } else if ("Update Member".equals(action)) {
            try {
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                String name = request.getParameter("name").trim();
                String email = request.getParameter("email").trim();
                long mobile = Long.parseLong(request.getParameter("mobile").trim());
                String genderCode = request.getParameter("gender");
                String address = request.getParameter("address").trim();

                if (name.isEmpty() || email.isEmpty() || genderCode == null || address.isEmpty()) {
                    request.setAttribute("message", "All fields are required.");
                } else {
                    Member member = new Member(memberId, name, email, mobile, Gender.fromCode(genderCode), address);
                    boolean success = membersDao.updateMember(member);

                    if (success) {
                        request.setAttribute("message", "Member updated successfully.");
                    } else {
                        request.setAttribute("message", "Failed to update member.");
                    }
                }
            } catch (Exception e) {
                request.setAttribute("message", "Error updating member.");
            }
        }

        request.getRequestDispatcher("update_member.jsp").forward(request, response);
    }


}
