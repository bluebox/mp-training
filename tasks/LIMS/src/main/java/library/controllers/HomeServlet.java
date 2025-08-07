package library.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            request.getRequestDispatcher("/HomeScreen.jsp").forward(request, response);
            return;
        }

        String targetJsp = null;
        String pageTitle = null;

        switch (action) {
            case "addBook":
                targetJsp = "/AddBookForm.jsp";
                pageTitle = "Add New Book";
                break;
            case "viewBooks":
                targetJsp = "/ViewBooksScreen.jsp";
                pageTitle = "View All Books";
                break;
            case "addMember":
                targetJsp = "/AddMemberForm.jsp";
                pageTitle = "Add New Member";
                break;
            case "viewMembers":
                targetJsp = "/ViewMembers.jsp";
                pageTitle = "View Members";
                break;
            case "issueBook":
                targetJsp = "/IssueBookForm.jsp";
                pageTitle = "Library Management System - Issue Book";
                break;
            case "returnBook":
                targetJsp = "/ReturnBookForm.jsp";
                pageTitle = "Library Management System - Return Book";
                break;
            case "viewIssueRecords":
                targetJsp = "/IssuedRecordsScreen.jsp"; 
                pageTitle = "Library Management System - All Issue Records";
                break;
            case "reports":
                targetJsp = "/ReportsScreen.jsp";
                pageTitle = "Library Management System - Reports";
                break;
            default:
                request.setAttribute("statusMessage", "Unknown action: " + action);
                request.getRequestDispatcher("/HomeScreen.jsp").forward(request, response);
                return;
        }

        if (targetJsp != null) {
            request.setAttribute("pageTitle", pageTitle);
            request.getRequestDispatcher(targetJsp).forward(request, response);
        } else {
            request.setAttribute("statusMessage", "Error loading page for action: " + action);
            request.getRequestDispatcher("/HomeScreen.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}