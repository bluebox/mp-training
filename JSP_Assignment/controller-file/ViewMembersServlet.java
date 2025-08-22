package com.library.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;

@WebServlet("/viewMembersServlet")
public class ViewMembersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Member> members = memberService.fetchAllMembers();
            request.setAttribute("members", members);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to load members: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ViewMembers.jsp");
        dispatcher.forward(request, response);
    }
}
