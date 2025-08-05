package com.lms.controller;

import com.lms.model.Member;
import com.lms.serviceimpl.MemberService;
import com.lms.util.Validator;
import com.lms.exceptions.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.getRequestDispatcher("add-member.jsp").forward(request, response);
    	
    	    
		request.setAttribute("page", "add-member.jsp");
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Member member = new Member();
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setMobile(request.getParameter("mobile"));
        member.setGender(request.getParameter("gender"));
        member.setAddress(request.getParameter("address"));

        try {
            Validator.validateMember(member);
            boolean success = memberService.addMember(member);
            request.setAttribute("message", success ? "Member added successfully!" : "Failed to add member.");
        } catch (InvalidInputException | DAOException e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        //request.getRequestDispatcher("add-member.jsp").forward(request, response);
        request.setAttribute("page", "add-member.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
