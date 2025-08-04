package com.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.ReportServiceInterface;
import com.lms.serviceimpl.ReportServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReportsServlet")
public class ReportsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ReportServiceInterface reportService = new ReportServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportType = request.getParameter("reportType");
        if (reportType == null || reportType.isBlank()) {
            reportType = "Overdue Books";
        }

        try {
            switch (reportType) {
                case "Overdue Books":
                    List<Book> overdueBooks = reportService.fetchOverdueBooks();
                    request.setAttribute("overdueBooks", overdueBooks);
                    break;

                case "Books by Category":
                    Map<String, Long> booksByCategory = reportService.fetchBookCountByCategory();
                    request.setAttribute("booksByCategory", booksByCategory);
                    break;

                case "Active Members":
                    List<Member> activeMembers = reportService.fetchMembersWithActiveIssues();
                    request.setAttribute("activeMembers", activeMembers);
                    break;

                default:
                    request.setAttribute("error", "Unknown report type.");
                    break;
            }

            request.setAttribute("reportType", reportType);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

       // request.getRequestDispatcher("Reports.jsp").forward(request, response);
        request.setAttribute("page", "Reports.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
