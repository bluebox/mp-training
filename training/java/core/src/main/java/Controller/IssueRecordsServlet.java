package controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import Service.ServiceLayer;
import Domain.Book;
import Domain.IssueRecord;

/**
 * Servlet implementation class IssueRecords
 */
@WebServlet("/issuerecords")
public class IssueRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public IssueRecords() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceLayer iss = new ServiceLayer();

	    try {
	    	List<IssueRecord> list=iss.getAllIssueRecords();
	        request.setAttribute("issueList", list);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/viewallissuerecords.jsp");
	        dispatcher.forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching issue records");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

	}

}
