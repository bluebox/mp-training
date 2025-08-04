package com.lms.controller;

import com.lms.model.Member;
import com.lms.serviceimpl.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {

    private final MemberService service = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String message = "";

        if ("Fetch Member".equals(action)) {
            String mobile = request.getParameter("searchMobile");
            try {
                Member member = service.getMemberByMobile(mobile);
                if (member == null) {
                    message = "Member not found with this mobile number.";
                }
                request.setAttribute("member", member);
            } catch (Exception e) {
                message = "Error: " + e.getMessage();
            }
        }

        if ("Update Member".equals(action)) {
            try {
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String mobile = request.getParameter("mobile");
                String gender = request.getParameter("gender");
                String address = request.getParameter("address");

                Member member = new Member(memberId, name, email, mobile, gender, address);
                boolean updated = service.updateMember(member);

                if (updated) {
                    message = "Member updated successfully.";
                    request.setAttribute("member", member);
                } else {
                    message = "Update failed.";
                }

            } catch (NumberFormatException e) {
                message = "Invalid Member ID.";
            } catch (Exception e) {
                message = "Unexpected error: " + e.getMessage();
            }
        }

        request.setAttribute("message", message);
        //request.getRequestDispatcher("update-member.jsp").forward(request, response);
        request.setAttribute("page", "update-member.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    	
        req.getRequestDispatcher("home.jsp?page=update-member.jsp").forward(req, resp);
    }
}
