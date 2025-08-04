package Controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        request.getRequestDispatcher("memberswithissuedbooks.jsp").forward(request, response);
    }
}

