package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/LoadMemberForEditController")
public class LoadMemberForEditController extends HttpServlet {
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String idParam = req.getParameter("memberId");
        try {
            int memberId = Integer.parseInt(idParam);
            Member member = memberService.fetchMemberById(memberId);
            if (member == null) {
                req.setAttribute("errorMessage", "Member not found");
                req.getRequestDispatcher("ViewMembers.jsp").forward(req, res);
            } else {
                req.setAttribute("member", member);
                req.getRequestDispatcher("UpdateMemberForm.jsp").forward(req, res);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Invalid member ID");
            req.getRequestDispatcher("ViewMembers.jsp").forward(req, res);
        }
    }
}
