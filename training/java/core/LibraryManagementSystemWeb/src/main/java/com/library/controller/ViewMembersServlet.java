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

@WebServlet("/viewMembers")
public class ViewMembersServlet extends HttpServlet {
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Member> members = memberService.fetchAllMembers();
            request.setAttribute("memberList", members);
            request.getRequestDispatcher("ViewMembers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Unable to load members.");
        }
    }
}