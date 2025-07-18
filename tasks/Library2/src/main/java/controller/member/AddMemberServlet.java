package controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Member;
import serviceimpl.MemberServiceImpl;

import java.io.IOException;

@WebServlet("/addmember")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/view/members/addMember.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		MemberServiceImpl memberService = new MemberServiceImpl();
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            String mobileStr = req.getParameter("mobile");
            String genderStr = req.getParameter("gender");

            if (name == null || email == null || address == null ||
                mobileStr == null || genderStr == null ||
                name.isEmpty() || email.isEmpty() || address.isEmpty() ||
                mobileStr.isEmpty() || genderStr.isEmpty()) {

                req.setAttribute("message", "All fields are required.");
                doGet(req, resp);
                return;
            }

            long mobile = Long.parseLong(mobileStr);
            char gender = genderStr.charAt(0);

            Member member = new Member(0, name, email, mobile, gender, address);
			memberService.addMember(member);

            req.setAttribute("message", "Member added successfully!");
        } catch (Exception e) {
            req.setAttribute("message", "Error: " + e.getMessage());
        }

        doGet(req, resp);
    }

}
