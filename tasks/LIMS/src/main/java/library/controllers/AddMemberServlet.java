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

@WebServlet("/addMember") 
public class AddMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumberStr = request.getParameter("phoneNumber");
        String genderStr = request.getParameter("gender"); 
        String address = request.getParameter("address");

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phoneNumber", phoneNumberStr);
        request.setAttribute("selectedGender", genderStr); 
        request.setAttribute("address", address);

        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            phoneNumberStr == null || phoneNumberStr.isEmpty() ||
            genderStr == null || genderStr.isEmpty() || 
            address == null || address.isEmpty()) {

            request.setAttribute("message", "Please fill in all fields.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
            return;
        }

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(phoneNumberStr);
            if (!phoneNumberStr.matches("\\d+")) { 
                 request.setAttribute("message", "Phone number must contain only digits."); 
                 request.setAttribute("messageType", "error");
                 request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
                 return;
            }
            if (phoneNumber <= 0) { 
                request.setAttribute("message", "Phone number cannot be zero or negative.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid phone number format. Please enter a numeric value.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
            return;
        }

        Gender gender;
        try {
            gender = Gender.valueOf(genderStr); 
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "Invalid gender selected.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
            return;
        }

        try {
            Member newMember = new Member(0, name, email, phoneNumber, gender, address);
            
            String resultMessage = memberService.addMember(newMember); 

            request.setAttribute("message", resultMessage);
            request.setAttribute("messageType", "success");
            
            request.removeAttribute("name");
            request.removeAttribute("email");
            request.removeAttribute("phoneNumber");
            request.removeAttribute("selectedGender");
            request.removeAttribute("address");

        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                request.setAttribute("message", "Error: Member with this email or phone number already exists.");
            } else {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("messageType", "error");
            System.err.println("LibraryException adding member: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("/AddMemberForm.jsp").forward(request, response);
        }
    }
}