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

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ReturnBookServlet() {
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

        IssueRecordDao dao = new IssueRecordDao();
        IssueRecord record = new IssueRecord(0, bookId, memberId, RecordStatus.RETURNED, LocalDate.now(), null);

        try {
            if(dao.returnBook(record)) {
            	response.getWriter().println("Book returned successfully");
            }
            else {
            	response.getWriter().println("Book returned failed");
            }
//            response.sendRedirect("view_issued_records.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error returning book.");
        }
    }

}
