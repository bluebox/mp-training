package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
    private final MemberServiceImplementation service = new MemberServiceImplementation();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("memberId"),
               name = req.getParameter("name"),
               email = req.getParameter("email"),
               mobile = req.getParameter("mobile"),
               genderParam = req.getParameter("gender"),
               address = req.getParameter("address");

        boolean hasError = false;
        if (name == null || name.trim().isEmpty()) { req.setAttribute("nameError", "Name is required"); hasError = true; }
        if (email == null || !email.trim().endsWith("@gmail.com")) { req.setAttribute("emailError", "Invalid email"); hasError = true; }
        if (mobile == null || !mobile.matches("\\d{10}")) { req.setAttribute("mobileError", "Mobile must be 10 digits"); hasError = true; }
        if (genderParam == null || genderParam.isEmpty()) { req.setAttribute("genderError", "Gender is required"); hasError = true; }
        if (address == null || address.trim().isEmpty()) { req.setAttribute("addressError", "Address is required"); hasError = true; }

        Member updated = new Member(
            Integer.parseInt(id),
            name.trim(),
            email.trim(),
            Long.parseLong(mobile),
            genderParam.equals("Male") ? 'M' : 'F',
            address.trim()
        );
        req.setAttribute("member", updated);

        if (hasError) {
        	
        	
            req.getRequestDispatcher("UpdateMemberForm.jsp").forward(req, resp);
            return;
        }

        try {
            service.updateMember(updated);
            List<Member> refreshed = service.getAllMembers();
            req.setAttribute("membersList", refreshed);
            
            req.getRequestDispatcher("ViewMembers.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Update failed");
            req.getRequestDispatcher("UpdateMemberForm.jsp").forward(req, resp);
        }
    }
}  