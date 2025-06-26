package com.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SqServlet
 */
@WebServlet("/sq")
public class SqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public SqServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
//	 HttpSession session=req.getSession();
//		int k=(int)session.getAttribute("k");
		
		int k=0;
		Cookie cookie[]=req.getCookies();
		for(Cookie c:cookie) {
			if(c.getName().equals("k"))
				k=Integer.parseInt(c.getValue());
		}
		
		k=k*k;
		
		PrintWriter out=res.getWriter();
		out.println("result is "+k);
		System.out.println("sql called");
	}

	
}
