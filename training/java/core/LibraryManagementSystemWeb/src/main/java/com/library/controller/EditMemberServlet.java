package com.library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
@WebServlet("/editMember")
public class EditMemberServlet extends HttpServlet {
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int memberId = Integer.parseInt(request.getParameter("id"));
            Member member = memberService.fetchMemberById(memberId);

            if (member == null) {
                response.getWriter().println("Member not found for ID: " + memberId);
                return;
            }

            request.setAttribute("member", member);
            request.getRequestDispatcher("editMember.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewMembers");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String mobileStr = request.getParameter("mobile").trim();
        String gender = request.getParameter("gender");
        String address = request.getParameter("address").trim();
        int memberId = Integer.parseInt(request.getParameter("id"));

        if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
            request.setAttribute("errorMsg", "All fields are required.");

            Member existing = null;
			try {
				existing = memberService.fetchMemberById(memberId);
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (existing != null) {
                request.setAttribute("member", existing);
            }

            request.getRequestDispatcher("editMember.jsp").forward(request, response);
            return;
        }

        try {
            long mobile = Long.parseLong(mobileStr);
            Member member = new Member(memberId, name, email, mobile, gender, address);
            boolean updated = memberService.modifyMember(member);

            if (updated) {
                request.setAttribute("successMsg", "Member updated successfully!");
            } else {
                request.setAttribute("errorMsg", "Update failed.");
            }

            request.setAttribute("member", member);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Server error.");
        }

        request.getRequestDispatcher("editMember.jsp").forward(request, response);
    }
}
