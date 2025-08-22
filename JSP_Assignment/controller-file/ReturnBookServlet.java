package com.library.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.library.dao.impl.IssueRecordDaoImplementation;
import com.library.model.IssueRecord;
import com.library.service.impl.IssueRecordServiceImplementation;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IssueRecordDaoImplementation issueDao = new IssueRecordDaoImplementation();
    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IssueRecord> issued;
		try {
			issued = issueService.getAllIssues();
		 
        for(IssueRecord i:issued) {
        	System.out.println(i.getIssueId()+" "+i.getStatus());
        }
        req.setAttribute("issuedRecords", issued); 
        req.getRequestDispatcher("ReturnBook.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String issueIdStr = req.getParameter("issueId");

        String statusMessage;
        String statusColor = "red";

        if (issueIdStr == null || issueIdStr.isEmpty()) {
            statusMessage = "Please select a record to return.";
        } else {
            try {
                int issueId = Integer.parseInt(issueIdStr);
                boolean success = issueService.returnBook(issueId);

                if (success) {
                    statusMessage = "Book returned successfully!";
                    statusColor = "green";
                } else {
                    statusMessage = "Failed to return book.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                statusMessage = "Error: " + e.getMessage();
            }
        }

        req.setAttribute("statusMessage", statusMessage);
        req.setAttribute("statusColor", statusColor);

        doGet(req, resp);
    }
}
