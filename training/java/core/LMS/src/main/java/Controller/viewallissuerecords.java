package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Domain.IssueRecord;
import Service.ServiceLayer;

/**
 * Servlet implementation class viewallissuerecords
 */
@WebServlet("/viewallissuerecords")
public class viewallissuerecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceLayer issueService = new ServiceLayer();
		List<IssueRecord> list = null;
		try {
			list = issueService.getAllIssueRecords();
			request.setAttribute("list", list);
			request.getRequestDispatcher("viewallissuerecords.jsp").forward(request,response);
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("error",e.getMessage());
			request.getRequestDispatcher("viewallissuerecords.jsp").forward(request, response);
		}
	}

}
