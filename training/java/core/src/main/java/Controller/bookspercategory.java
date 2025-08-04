package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.ServiceLayer;

///**
// * Servlet implementation class bookspercategory
// */
//@WebServlet("/bookspercategory")
//public class bookspercategory extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public bookspercategory() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
//@WebServlet("/bookspercategory")
//public class bookspercategory extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServiceLayer s = new ServiceLayer();
//        Map<String, Long> getBookCountByCategory = null;
//		try {
//			getBookCountByCategory = s.getBookCountByCategory();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//        request.setAttribute("categoryMap", getBookCountByCategory);
//        request.getRequestDispatcher("/WEB-INF/views/bookspercategory.jsp").forward(request, response);
//    }
//}

@WebServlet("/bookspercategory")
public class bookspercategory extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceLayer service = new ServiceLayer();
        Map<String, Long> map = null;
		try {
			map = service.getBookCountByCategory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("categoryMap", map);
        request.getRequestDispatcher("/WEB-INF/views/bookspercategory.jsp").forward(request, response);
    }
}


