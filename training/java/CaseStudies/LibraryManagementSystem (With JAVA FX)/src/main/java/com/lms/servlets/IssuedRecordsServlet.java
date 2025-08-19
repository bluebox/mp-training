package com.lms.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.lms.model.Issue_Records;
import com.lms.service.IssueLogService;
import com.lms.service.impl.IssueLogServiceImpl;


@WebServlet("/IssuedRecordsServlet")
public class IssuedRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IssueLogService issueRecordsService;
    
	public void init() {
		issueRecordsService = new IssueLogServiceImpl();
	}
   
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		List<Issue_Records> issue_records = issueRecordsService.getAllIssuedRecords();
		request.setAttribute("isuueRecords", issue_records);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


}
