package library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;
import library.service.MemberServiceImpl;
import library.service.interfaces.MemberService;

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberService memberService;

    public UpdateMemberServlet() {
        super();
        this.memberService = new MemberServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberID");
        if (memberIdStr == null || memberIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "Member ID not provided.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            int memberId = Integer.parseInt(memberIdStr);
            Member member = memberService.getMemberById(memberId);
            
            if (member == null) {
                request.setAttribute("errorMessage", "Member with ID " + memberId + " not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("member", member);
            request.getRequestDispatcher("/updateMemberForm.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid member ID format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberID");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumberStr = request.getParameter("phoneNumber");
        String genderStr = request.getParameter("gender");
        String address = request.getParameter("address");

        try {
            int memberId = Integer.parseInt(memberIdStr);
            long phoneNumber = Long.parseLong(phoneNumberStr);
            
            Gender gender = null;
            if ("M".equals(genderStr)) gender = Gender.MALE;
            else if ("F".equals(genderStr)) gender = Gender.FEMALE;
            else if ("O".equals(genderStr)) gender = Gender.OTHER;
            
            Member updatedMember = new Member(memberId, name, email, phoneNumber, gender, address);
            memberService.updateMember(updatedMember);
            
            request.getSession().setAttribute("successMessage", "Member with ID " + memberId + " updated successfully.");
            
            response.sendRedirect(request.getContextPath() + "/viewAllMembers");

        } catch (NumberFormatException e) {
            int memberId = Integer.parseInt(memberIdStr);
            Member member = memberService.getMemberById(memberId);
            request.setAttribute("member", member);
            request.setAttribute("statusMessage", "Invalid phone number format.");
            request.setAttribute("statusType", "error");
            request.getRequestDispatcher("/updateMemberForm.jsp").forward(request, response);
        } catch (LibraryException e) {
            int memberId = Integer.parseInt(memberIdStr);
            Member member = memberService.getMemberById(memberId);
            request.setAttribute("member", member);
            request.setAttribute("statusMessage", e.getMessage());
            request.setAttribute("statusType", "error");
            request.getRequestDispatcher("/updateMemberForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}