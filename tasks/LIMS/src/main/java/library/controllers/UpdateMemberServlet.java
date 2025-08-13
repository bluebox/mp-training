package library.controllers;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.memberService = new MemberServiceImpl();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberId");
        Member memberToUpdate = null;

        if (memberIdStr != null && !memberIdStr.isEmpty()) {
            try {
                int memberId = Integer.parseInt(memberIdStr);
                memberToUpdate = memberService.getMemberById(memberId);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Member ID format.");
                request.setAttribute("messageType", "error");
            } catch (LibraryException e) {
                request.setAttribute("message", "Error retrieving member: " + e.getMessage());
                request.setAttribute("messageType", "error");
                System.err.println("Error getting member by ID: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (memberToUpdate != null) {
            request.setAttribute("memberID", memberToUpdate.getMemberID());
            request.setAttribute("name", memberToUpdate.getName());
            request.setAttribute("email", memberToUpdate.getEmail());
            request.setAttribute("phoneNumber", String.valueOf(memberToUpdate.getPhoneNumber())); 
            request.setAttribute("selectedGender", memberToUpdate.getGender().toString());
            request.setAttribute("address", memberToUpdate.getAddress());
        } else {
            if (request.getAttribute("message") == null) {
                 request.setAttribute("message", "Member not found or no Member ID provided.");
                 request.setAttribute("messageType", "error");
            }
        }

        request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String memberIdStr = request.getParameter("memberID"); 
        String newName = request.getParameter("name");
        String newEmail = request.getParameter("email");
        String newPhoneNumberText = request.getParameter("phoneNumber");
        String newGenderStr = request.getParameter("gender");
        String newAddress = request.getParameter("address");

        request.setAttribute("memberID", memberIdStr);
        request.setAttribute("name", newName);
        request.setAttribute("email", newEmail);
        request.setAttribute("phoneNumber", newPhoneNumberText);
        request.setAttribute("selectedGender", newGenderStr);
        request.setAttribute("address", newAddress);

        if (memberIdStr == null || memberIdStr.isEmpty() ||
            newName == null || newName.isEmpty() ||
            newEmail == null || newEmail.isEmpty() ||
            newPhoneNumberText == null || newPhoneNumberText.isEmpty() ||
            newGenderStr == null || newGenderStr.isEmpty() ||
            newAddress == null || newAddress.isEmpty()) {

            request.setAttribute("message", "Please fill in all fields.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
            return;
        }

        int memberID;
        long newPhoneNumber;
        Gender newGender;

        try {
            memberID = Integer.parseInt(memberIdStr);
            newPhoneNumber = Long.parseLong(newPhoneNumberText);
            if (!newPhoneNumberText.matches("\\d+")) {
                 request.setAttribute("message", "Phone number must contain only digits.");
                 request.setAttribute("messageType", "error");
                 request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
                 return;
            }
            if (newPhoneNumber <= 0) {
                request.setAttribute("message", "Phone number cannot be zero or negative.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
                return;
            }
            newGender = Gender.valueOf(newGenderStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid ID or Phone number format.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
            return;
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "Invalid gender selected.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
            return;
        }

        try {
            
            Member originalMember = memberService.getMemberById(memberID);
            if (originalMember == null) {
                request.setAttribute("message", " Member with ID " + memberID + " not found for update.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
                return;
            }

            Member updatedMember = new Member(
                memberID,
                newName,
                newEmail,
                newPhoneNumber,
                newGender, 
                newAddress
            );
            
            memberService.updateMember(updatedMember); 
            response.sendRedirect("viewMembers?message=Member+ID+" + memberID + "+updated+successfully.&type=success");
            return;

        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                request.setAttribute("message", " Member with this email or phone number already exists.");
            } else {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("messageType", "error");
            System.err.println("LibraryException updating member: " + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
            request.getRequestDispatcher("/UpdateMemberForm.jsp").forward(request, response);
        }
    }
}