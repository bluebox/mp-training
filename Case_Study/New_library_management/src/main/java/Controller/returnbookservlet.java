package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import Service.IssueRecordService;

/**
 * Servlet implementation class returnbookservlet
 */
@WebServlet("/returnbook")
public class returnbookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IssueRecordService service=new IssueRecordService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnbookservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/returnbookform.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String bookid=request.getParameter("bookid");
		String memberid=request.getParameter("memberid");
		String msg=null;
		if (bookid==null || memberid==null) {
			msg="bookid and memberid are required";
			
		}else {try {
			int bookId=Integer.parseInt(bookid);
			int memberId=Integer.parseInt(memberid);
			boolean sucess=service.returnBook(bookId,memberId);
			if (sucess) {
                msg = "Book returned successfully!";
            } else {
                msg = "member or book are not available to return";
            }
		}catch(NumberFormatException e) {
			msg="invalid member id or bookid";
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			msg = "Database error while issuing book.";
			e.printStackTrace();
		}
		
	}
		request.setAttribute("message", msg);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/returnbookresult.jsp");
        dispatcher.forward(request, response);

	}

}
