package controller;

import model.Member;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewMembers")
public class ViewMembersServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members;
        try {
            members = memberService.getAllMembers();
        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Error loading members: " + e.getMessage() + "</h2>");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Members List</title></head><body>");
        out.println("<h1>Members</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Name</th><th>Email</th><th>Mobile</th></tr>");

        for (Member m : members) {
            out.printf("<tr><td>%s</td><td>%s</td><td>%d</td></tr>", 
                m.getName(), m.getEmail(), m.getMobile());
        }

        out.println("</table>");
        out.println("<br><a href='viewMembers.html'>Back</a>");
        out.println("</body></html>");
    }
}
