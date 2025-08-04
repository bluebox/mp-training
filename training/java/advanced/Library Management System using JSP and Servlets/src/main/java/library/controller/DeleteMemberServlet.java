package library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Member;
import library.service.MemberServiceImpl;
import library.service.interfaces.MemberService;

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private MemberService memberService;

    public DeleteMemberServlet() {
        super();
        this.memberService = new MemberServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberIdStr = request.getParameter("memberID");
        
        if (memberIdStr == null || memberIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid member ID provided for deletion.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            int memberId = Integer.parseInt(memberIdStr);
            Member memberToDelete = new Member();
            memberToDelete.setMemberID(memberId);
            memberService.deleteMember(memberToDelete);
            request.getSession().setAttribute("successMessage", "Member with ID " + memberId + " deleted successfully.");
            response.sendRedirect(request.getContextPath() + "/viewAllMembers");
            
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid member ID format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (LibraryException e) {
            request.setAttribute("errorMessage", "Error deleting member: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}
}