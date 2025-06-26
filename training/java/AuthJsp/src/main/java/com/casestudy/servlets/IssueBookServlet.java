package com.casestudy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casestudy.dao.IssueRecordDao;
import com.casestudy.domain.IssueRecord;
import com.casestudy.domain.RecordStatus;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IssueBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        IssueRecord record = new IssueRecord(0, bookId, memberId, RecordStatus.ISSUED, LocalDate.now(), null);

        try {
            IssueRecordDao dao = new IssueRecordDao();
            if (!dao.alreadyIssued(record)) {
                if(dao.issueBook(record)) {
                	response.getWriter().println("Book issued successfully");
                }
                else {
                	response.getWriter().println("Book issued failed");
                }
                
//                response.sendRedirect("view_issued_records.jsp");
            } else {
                response.getWriter().println("Book is already issued to this member.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error issuing book.");
        }
    }


}
