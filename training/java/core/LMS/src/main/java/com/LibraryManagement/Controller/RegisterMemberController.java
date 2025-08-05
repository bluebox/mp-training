package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/RegisterMemberController")
public class RegisterMemberController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        boolean hasError = false;

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("nameError", "Name is required *");
            hasError = true;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email is required *");
            hasError = true;
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("emailError", "Enter valid email address *");
            hasError = true;
        }

        if (mobile == null || mobile.trim().isEmpty()) {
            request.setAttribute("mobileError", "Mobile is required *");
            hasError = true;
        } else if (!mobile.matches("\\d{10}")) {
            request.setAttribute("mobileError", "Mobile must be exactly 10 digits *");
            hasError = true;
        }

        if (gender == null || gender.trim().isEmpty()) {
            request.setAttribute("genderError", "Gender is required *");
            hasError = true;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("addressError", "Address is required *");
            hasError = true;
        }

        if (hasError) {
      
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);
            
            request.getRequestDispatcher("AddMember.jsp").forward(request, response);
            return;
        }

        try {
            Member member = new Member(
                name.trim(),
                email.trim(),
                Long.parseLong(mobile.trim()),
                "Male".equals(gender) ? 'M' : 'F',
                address.trim()
            );
            int id = memberService.registerMember(member);
         
            request.setAttribute("successMessage", "Member registered successfully! Allotted member ID: " + id);
            request.getRequestDispatcher("AddMember.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to register member.");
            request.getRequestDispatcher("AddMember.jsp").forward(request, response);
        }
    }
}
