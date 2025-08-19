// ReportsServlet.java
package com.lms.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.model.OverDueList;
import com.lms.model.ReportMember;
import com.lms.service.BookService;
import com.lms.service.IssueLogService;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.IssueLogServiceImpl;

@WebServlet("/ReportsServlet")
public class ReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private IssueLogService issueLogService;

    public void init() {
        bookService = new BookServiceImpl();
        issueLogService = new IssueLogServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{ 
        
        // Get overdue books
        List<OverDueList> overdueBooks = issueLogService.getOverDueBooks();
        request.setAttribute("overdueBooks", overdueBooks);

        // Get category statistics
        Map<String, Long> categoryStats = bookService.getBooksByCategory();
        request.setAttribute("categoryStats", categoryStats);

        // Get member book statistics
        List<ReportMember> memberStats = issueLogService.booksOfMemberReport();
        request.setAttribute("memberStats", memberStats);
        
        request.setAttribute("fileToRender", "Reports.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
    }
}
