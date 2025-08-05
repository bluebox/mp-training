package com.library.controller;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-member")
public class AddMemberServlet extends HttpServlet {

    private final MemberServiceImplementation service = new MemberServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String email = req.getParameter("email").trim();
        String phone = req.getParameter("phone").trim();
        String gender = req.getParameter("gender");
        String address = req.getParameter("address").trim();

        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);
        req.setAttribute("gender", gender);
        req.setAttribute("address", address);

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || gender == null || gender.isEmpty() || address.isEmpty()) {
            setError(req, "All fields are required.");
            req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            setError(req, "Name must contain only letters.");
            req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            setError(req, "Invalid email format.");
            req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            setError(req, "Mobile number must be 10 digits.");
            req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
            return;
        }

        if (address.length() < 5) {
            setError(req, "Address must be at least 5 characters long.");
            req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
            return;
        }

        char genderChar = gender.equalsIgnoreCase("Male") ? 'M' : 'F';

        Member member = new Member(name, email, Long.parseLong(phone), String.valueOf(genderChar), address);

        try {
            if (service.doesMemberExist(email, Long.parseLong(phone))) {
                setError(req, "Member with this email or phone already exists.");
            } else if (service.registerMember(member)) {
                req.setAttribute("message", "Member added successfully!");
                req.setAttribute("messageColor", "success");

                req.setAttribute("name", "");
                req.setAttribute("email", "");
                req.setAttribute("phone", "");
                req.setAttribute("gender", "");
                req.setAttribute("address", "");
            } else {
                setError(req, "Failed to add member.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            setError(req, "Server error: " + e.getMessage());
        }

        req.getRequestDispatcher("AddMemberForm.jsp").forward(req, resp);
    }

    private void setError(HttpServletRequest req, String message) {
        req.setAttribute("message", message);
        req.setAttribute("messageColor", "error");
    }
}
