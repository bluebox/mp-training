package com.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;


@WebServlet("/updateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final MemberServiceImplementation memService = new MemberServiceImplementation();
	private int id;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String memIdParam = request.getParameter("memberId");

        if (memIdParam == null || memIdParam.trim().isEmpty()) {
            request.setAttribute("error", "No memberId provided");
            request.getRequestDispatcher("/viewMembersServlet").forward(request, response);
            return;
        }

        try {
            int memberId = Integer.parseInt(memIdParam);
            Member member = memService.fetchMemberById(memberId);

            if (member == null) {
                request.setAttribute("error", "Member not found for ID: " + memberId);
                request.getRequestDispatcher("/viewMembersServlet").forward(request, response);
                return;
            }

            request.setAttribute("member", member);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateMember.jsp");
            dispatcher.forward(request, response);
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching Member details: " + e.getMessage());
            request.getRequestDispatcher("/viewMembersServlet").forward(request, response);
        }
    }
    
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("memberId"));
		String name=request.getParameter("name");
		long mobile=Long.parseLong(request.getParameter("Mobile"));
		String email=request.getParameter("Email");
		String gender=request.getParameter("gender");
		String address=request.getParameter("Address");
		
		 try {
		        memService.modifyMember(new Member(name,email,mobile,gender,address));
		        
		        RequestDispatcher reqdis=request.getRequestDispatcher("/WEB-INF/results/UpdateMemberResult.jsp");
				reqdis.forward(request, response);

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "An error occurred while updating the book.");
		        request.getRequestDispatcher("/WEB-INF/results/UpdateMemberResult.jsp").forward(request, response);
		    }
		    
		
		
	}
}
