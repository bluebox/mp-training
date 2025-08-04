package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import domain.checking_enum.Gender;
import domain.Member;
import Service.MemberService;

@WebServlet("/AddMemberController")
public class AddMemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService service = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobileStr = request.getParameter("mobile");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        try {
            Long mobile = Long.parseLong(mobileStr.trim());

            Gender genderEnum = Gender.getstatus(gender.trim().toUpperCase().substring(0, 1));
            if (genderEnum == null) {
                throw new IllegalArgumentException("Invalid gender value provided.");
            }

            Member member = new Member(name, email, mobile, address, genderEnum);

            service.addMember(member);

            request.setAttribute("message", "Added new member successfully");
            request.getRequestDispatcher("AddMember.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid mobile number format.");
            request.getRequestDispatcher("/WEB-INF/views/AddMember.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding member: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/AddMember.jsp").forward(request, response);
        }
    }
}
