package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Member;
import Service.ServiceLayer;


@WebServlet("/memberswithissued")
public class memberswithissuedbooks extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceLayer service = new ServiceLayer();
        List<Member> issuedMembers = null;
		try {
			issuedMembers = service.getMembersWithActiveIssuedBooks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("members", issuedMembers);
        request.getRequestDispatcher("/WEB-INF/views/memberswithissuedbooks.jsp").forward(request, response);
    }
}

