package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pojo.Member;
import Service.LibraryService;

/**
 * Servlet implementation class UpdateMember
 */
@WebServlet("/updateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int memberId = Integer.parseInt(request.getParameter("memberId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			long mobile =Long.parseLong(request.getParameter("mobile"));
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");

			Member member=new Member();
			member.setMemberId(memberId);
			member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(gender.toUpperCase().charAt(0));
            member.setAddress(address);
            LibraryService lib;
            PrintWriter out = response.getWriter();
			try {
				lib = new LibraryService();
				lib.updateMember(member);
				out.println("<b>Member updated Successfully!</b>");
			} catch (Exception e) {
				out.println("<b>Member not updated..</b>");
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
