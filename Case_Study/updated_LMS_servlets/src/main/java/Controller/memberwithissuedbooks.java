package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Service.Reports;
import domain.Member;

/**
 * Servlet implementation class memberwithissuedbooks
 */
@WebServlet("/memberwithissuedbooks")
public class memberwithissuedbooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberwithissuedbooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Reports service = new Reports();
        List<Member> issuedMembers = null;
		try {
			issuedMembers = service.members_with_statusissue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("members", issuedMembers);
        request.getRequestDispatcher("memberswithissuedbooks.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
