package com.library.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;

@WebServlet("/addMemberServlet")
public class AddMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/AddMember.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        try {
            long mobile = Long.parseLong(mobileStr);

            if (memberService.doesMemberExist(email, mobile)) {
                request.setAttribute("error", "Member with this email or mobile number already exists.");
            } else {
                Member member = new Member(0, name, email, mobile, gender, address);
                memberService.registerMember(member);
                request.setAttribute("message", "Member added successfully!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid mobile number format.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while adding the member: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/AddMemberResult.jsp");
        dispatcher.forward(request, response);
    }
}
