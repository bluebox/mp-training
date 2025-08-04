package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.library.model.Member;
import com.library.service.MemberServiceImplementation;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MemberServiceImplementation memberService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("view")) {
            handleViewMembers(request, response);
        } else if (action.equalsIgnoreCase("edit")) {
            handleEditMember(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            handleUpdate(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        boolean valid = true;
        String nameError = "", emailError = "", mobileError = "", genderError = "", addressError = "";

        if (name == null || name.trim().isEmpty()) {
            nameError = "Name is required *";
            valid = false;
        }
        if (email == null || email.trim().isEmpty()) {
            emailError = "Email is required *";
            valid = false;
        } else if (!email.endsWith("@gmail.com")) {
            emailError = "Email must end with @gmail.com";
            valid = false;
        }
        if (mobileStr == null || mobileStr.trim().isEmpty()) {
            mobileError = "Mobile is required *";
            valid = false;
        } else if (!mobileStr.matches("\\d{10}")) {
            mobileError = "Mobile must be exactly 10 digits";
            valid = false;
        } else if (isMobileDuplicate(Long.parseLong(mobileStr))) {
            mobileError = "Mobile number already exists!";
            valid = false;
        }

        if (gender == null || gender.trim().isEmpty()) {
            genderError = "Gender is required *";
            valid = false;
        }
        if (address == null || address.trim().isEmpty()) {
            addressError = "Address is required *";
            valid = false;
        }

        if (!valid) {
            request.setAttribute("nameError", nameError);
            request.setAttribute("emailError", emailError);
            request.setAttribute("mobileError", mobileError);
            request.setAttribute("genderError", genderError);
            request.setAttribute("addressError", addressError);

            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobileStr);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);

            request.getRequestDispatcher("/AddMember.jsp").forward(request, response);
            return;
        }

        long mobile = Long.parseLong(mobileStr);
        String genderCode = gender.equalsIgnoreCase("Male") ? "M" : "F";

        Member member = new Member(name.trim(), email.trim(), mobile, genderCode, address.trim());
        try {
            int id = memberService.registerMember(member);
            sendAlertAndRedirect(response, "Member registered successfully! Member ID: " + id,
                    "MemberController?action=view");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private boolean isMobileDuplicate(long mobile) {
        List<Member> members = memberService.getAllMembers();
        return members.stream().anyMatch(m -> m.getMobile() == mobile);
    }

    private void handleViewMembers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Member> members = memberService.getAllMembers();
            request.setAttribute("members", members);
            request.getRequestDispatcher("/ViewMembers.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    

    private void handleEditMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberId");
        if (memberIdStr == null || memberIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing member ID");
            return;
        }
        int memberId = Integer.parseInt(memberIdStr);

        try {
            Member member = memberService.fetchMemberById(memberId);
            if (member == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
                return;
            }

            request.setAttribute("member", member);
            request.getRequestDispatcher("/UpdateMember.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        boolean valid = true;
        String nameError = "", emailError = "", mobileError = "", genderError = "", addressError = "";

        if (name == null || name.trim().isEmpty()) {
            nameError = "Name is required *";
            valid = false;
        }
        if (email == null || email.trim().isEmpty()) {
            emailError = "Email is required *";
            valid = false;
        } else if (!email.endsWith("@gmail.com")) {
            emailError = "Email must end with @gmail.com";
            valid = false;
        }
        if (mobileStr == null || mobileStr.trim().isEmpty()) {
            mobileError = "Mobile is required *";
            valid = false;
        } else if (!mobileStr.matches("\\d{10}")) {
            mobileError = "Mobile must be exactly 10 digits";
            valid = false;
        }

        if (gender == null || gender.trim().isEmpty()) {
            genderError = "Gender is required *";
            valid = false;
        }
        if (address == null || address.trim().isEmpty()) {
            addressError = "Address is required *";
            valid = false;
        }

        if (!valid) {
            Member member = new Member(memberId, name, email, Long.parseLong(mobileStr),
                    gender.equalsIgnoreCase("Male") ? "M" : "F", address);
            request.setAttribute("member", member);

            request.setAttribute("nameError", nameError);
            request.setAttribute("emailError", emailError);
            request.setAttribute("mobileError", mobileError);
            request.setAttribute("genderError", genderError);
            request.setAttribute("addressError", addressError);

            request.getRequestDispatcher("/UpdateMember.jsp").forward(request, response);
            return;
        }

        long mobile = Long.parseLong(mobileStr);
        String genderCode = gender.equalsIgnoreCase("Male") ? "M" : "F";

        Member member = new Member(memberId, name.trim(), email.trim(), mobile, genderCode, address.trim());
        try {
            memberService.updateMember(member);
            sendAlertAndRedirect(response, "Member updated successfully!", "MemberController?action=view");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void sendAlertAndRedirect(HttpServletResponse response, String message, String redirectUrl)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");
        out.println("window.location = '" + redirectUrl + "';");
        out.println("</script>");
    }
}
