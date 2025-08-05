package com.lms.controller;

import com.lms.model.Member;
import com.lms.serviceimpl.MemberService;
import com.lms.exceptions.DAOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewMembers")
public class ViewMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Member> members = memberService.getAllMembers();
            request.setAttribute("members", members);
            System.out.println("Fetched Members Count: " + members.size()); 
            //request.getRequestDispatcher("view-members.jsp").forward(request, response);
            request.setAttribute("page", "view-members.jsp");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace(); 
            request.setAttribute("error", "Failed to fetch members: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
