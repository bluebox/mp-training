package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Member;
import serviceimpl.MemberServiceImpl;

import java.io.IOException;

@WebServlet("/updatemember")
public class UpdateMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberServiceImpl memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int memberId = Integer.parseInt(req.getParameter("memberId"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            Long mobile = Long.parseLong(req.getParameter("mobile"));
            Character gender = req.getParameter("gender").charAt(0);
            String address = req.getParameter("address");
            Member member = new Member(memberId, name, email, mobile, gender, address);
            memberService.updateMember(member);
            req.setAttribute("success", "Member updated successfully.");
        } catch (Exception e) {
            req.setAttribute("error", "Unable to update member: " + e.getMessage());
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/members/updateMember.jsp").forward(req, resp);
    }
}
