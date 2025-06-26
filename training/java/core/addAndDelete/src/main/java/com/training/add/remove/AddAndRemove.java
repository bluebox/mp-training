package com.training.add.remove;

import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class AddAndRemove
 */
@WebServlet("/AddAndRemove")
public class AddAndRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 out.print("<h3>Hello servlet World</h3>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	}

