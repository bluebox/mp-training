package controller;

import enums.Gender;
import model.Member;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String memberIdStr = request.getParameter("memberId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String genderStr = request.getParameter("gender");
        String address = request.getParameter("address");

        if (memberIdStr == null || memberIdStr.isEmpty() ||
            name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            mobileStr == null || mobileStr.isEmpty() ||
            genderStr == null || genderStr.isEmpty() ||
            address == null || address.isEmpty()) {

            response.getWriter().println("<p style='color:red;'>All fields are required.</p>");
            return;
        }

        try {
            int memberId = Integer.parseInt(memberIdStr);
            long mobile = Long.parseLong(mobileStr);

            Gender gender;
            switch (genderStr.toLowerCase()) {
                case "male":
                    gender = Gender.Male;
                    break;
                case "female":
                    gender = Gender.Female;
                    break;
                case "other":
                    gender = Gender.Other;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid gender value");
            }

            Member member = new Member();
            member.setMemberId(memberId);
            member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(gender);
            member.setAddress(address);

            memberService.addMember(member);

            response.getWriter().println("<p style='color:green;'>Member added successfully!</p>");

        } catch (NumberFormatException e) {
            response.getWriter().println("<p style='color:red;'>Member ID and Mobile must be numeric.</p>");
        } catch (Exception e) {
            response.getWriter().println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
