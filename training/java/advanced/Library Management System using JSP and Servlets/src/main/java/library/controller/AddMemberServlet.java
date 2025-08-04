package library.controller;

import java.io.IOException;

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

	public AddMemberServlet() {
		super();
		this.memberService = new MemberServiceImpl();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/addMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumberStr = request.getParameter("phoneNumber");
		String genderCode = request.getParameter("gender");
		String address = request.getParameter("address");
		try {
			Member newMember = new Member();
			newMember.setName(name);
			newMember.setEmail(email);
			long phoneNumber = Long.parseLong(phoneNumberStr);
			newMember.setPhoneNumber(phoneNumber);
			if (genderCode != null && !genderCode.isEmpty()) {
				newMember.setGender(Gender.fromCode(genderCode.charAt(0)));
			}
			newMember.setAddress(address);
			String memberID = memberService.addMember(newMember);
			request.setAttribute("successMessage", "Member added successfully with ID: " + memberID);
			request.getRequestDispatcher("/addMember.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid phone number format. Please enter a valid number.");
			request.getRequestDispatcher("/addMember.jsp").forward(request, response);
		} catch (LibraryException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/addMember.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
			request.getRequestDispatcher("/addMember.jsp").forward(request, response);
		}
	}
}