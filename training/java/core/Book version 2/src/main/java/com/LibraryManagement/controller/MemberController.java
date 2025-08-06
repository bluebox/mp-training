package com.LibraryManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.Implementation.MemberServiceImplementation;

@WebServlet("/memberController")
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static MemberServiceImplementation memberService = new MemberServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            handleView(request, response);
        } else if ("update".equals(action)) {
            handleUpdateForm(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                handleAdd(request, response);
            } else if ("saveUpdate".equals(action)) {
                handleSubmitUpdate(request, response);
            } else if ("view".equals(action)) {
                handleView(request, response);
            } else if ("update".equals(action)) {
                handleLoadUpdateForm(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/views/Members/members.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("/views/Members/viewMember.jsp").forward(request, response);
        }
    }

    private void handleLoadUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    	try {
            int memberId = Integer.parseInt(request.getParameter("memberId"));
            Member member = memberService.fetchMemberById(memberId);
            if (member != null) {
                request.setAttribute("member", member);
                request.getRequestDispatcher("/views/Members/updateMember.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Member not found.");
                request.setAttribute("messageColor", "red");
                handleView(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to load update form", e);
        }
		
	}

	private void handleAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String mobile=request.getParameter("mobile");
        String address = request.getParameter("address");

        boolean isValid = true;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Name cannot be empty");
            isValid = false;
        }
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email cannot be empty");
            isValid = false;
        }
        if (gender == null || (gender.equals("gender"))) {
            request.setAttribute("genderError", "Please select a valid gender");
            isValid = false;
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            request.setAttribute("mobileError", "Mobile cannot be empty");
            isValid = false;
        }
        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("addressError", "Address cannot be empty");
            isValid = false;
        }

        if (!isValid) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("mobile", mobile);
            request.setAttribute("address", address);

            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/views/Members/addMember.jsp").forward(request, response);
        } else {
        	Long mobileu = Long.parseLong(mobile);
            Member newMember = new Member(name, email,  mobileu ,gender.charAt(0), address);
            int id = memberService.registerMember(newMember);
            request.setAttribute("message", "Successfully added member. Member ID: " + id);
            request.setAttribute("messageColor", "green");

            request.setAttribute("name", "");
            request.setAttribute("email", "");
            request.setAttribute("gender", "");
            request.setAttribute("mobile", "");
            request.setAttribute("address", "");
            request.getRequestDispatcher("/views/Members/addMember.jsp").forward(request, response);
        }
    }

    private void handleView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Member> members = memberService.getAllMembers();
        request.setAttribute("memberList", members);
        request.getRequestDispatcher("/views/Members/viewMember.jsp").forward(request, response);
    }

    private void handleUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int memberId = Integer.parseInt(request.getParameter("memberId"));
            Member member = memberService.fetchMemberById(memberId);
            if (member != null) {
                request.setAttribute("member", member);
                request.getRequestDispatcher("/views/Members/updateMember.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Member not found.");
                request.setAttribute("messageColor", "red");
                handleView(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to load update form", e);
        }
    }

    private void handleSubmitUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");

        boolean isValid = true;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Name cannot be empty");
            isValid = false;
        }
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email cannot be empty");
            isValid = false;
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            request.setAttribute("mobileError", "Mobile cannot be empty");
            isValid = false;
        } else {
            try {
                Long.parseLong(mobile.trim()); 
            } catch (NumberFormatException e) {
                request.setAttribute("mobileError", "Mobile must be numeric");
                isValid = false;
            }
        }
        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("addressError", "Address cannot be empty");
            isValid = false;
        }

        int memberId = -1;
        try {
            memberId = Integer.parseInt(memberIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid Member ID");
            isValid = false;
        }

        if (!isValid) {
            try {
                Member member = memberService.fetchMemberById(memberId);
                if (member != null) {
                    request.setAttribute("member", member);
                }
            } catch (Exception e) {}
            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/views/Members/updateMember.jsp").forward(request, response);
            return;
        }
        try {
            Member existingMember = memberService.fetchMemberById(memberId);
            if (existingMember == null) {
                request.setAttribute("message", "Member not found for update.");
                request.setAttribute("messageColor", "red");
                handleView(request, response);
                return;
            }

            boolean noChanges =
                name.trim().equals(existingMember.getName().trim()) &&
                email.trim().equals(existingMember.getEmail().trim()) &&
                gender.equals(String.valueOf(existingMember.getGender())) &&
                mobile.trim().equals(String.valueOf(existingMember.getMobile())) &&
                address.trim().equals(existingMember.getAddress().trim());

            if (noChanges) {
                request.setAttribute("member", existingMember);
                request.setAttribute("message", "No changes detected.");
                request.setAttribute("messageColor", "red");
                request.getRequestDispatcher("/views/Members/updateMember.jsp").forward(request, response);
                return;
            }
            
            existingMember.setName(name);
            existingMember.setEmail(email);
            existingMember.setGender(gender.charAt(0)); 
            existingMember.setMobile(Long.parseLong(mobile.trim()));
            existingMember.setAddress(address);

            memberService.updateMember(existingMember);

            request.setAttribute("message", "Member updated successfully.");
            request.setAttribute("messageColor", "green");
            request.getRequestDispatcher("/views/Members/updateMember.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/memberController?action=view");
        }
         catch (Exception e) {
            throw new ServletException("Failed to update member", e);
        }
    }
}
