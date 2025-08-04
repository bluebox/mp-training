package Controller;
import Service.IssueRecordService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import domain.Issue_records;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
	    IssueRecordService iss = new IssueRecordService();

	    try {
	        List<Issue_records> list = iss.getAllIssuedRecords(); 
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
//		 Issue_records i = new Issue_records();
//        i.getBookid();
//        i.getMemberid();
//        i.getStatus_issue();
//        i.getIssuedate();
//        i.getReturndate();
//        List<Issue_records>  list1=getAllIssuedRecords()
	}

}
