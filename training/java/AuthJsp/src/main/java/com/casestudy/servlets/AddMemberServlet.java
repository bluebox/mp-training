package com.casestudy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casestudy.dao.MembersDao;
import com.casestudy.domain.Gender;
import com.casestudy.domain.Member;

@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        long mobile = Long.parseLong(request.getParameter("mobile"));
	        String gender = request.getParameter("gender");
	        String address = request.getParameter("address");

	        Member member = new Member(0, name, email, mobile, Gender.fromCode(gender), address);

	        try {
	            if(new MembersDao().addMember(member)) {
	            	 response.getWriter().println("success adding member.");
	            }
	            else {
	            	 response.getWriter().println("Error adding member.");
	            }
//	            response.sendRedirect("view_members.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error adding member.");
	        }
	    }
	


}
