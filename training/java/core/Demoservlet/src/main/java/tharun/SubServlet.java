package tharun;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/SubServlet")
public class SubServlet extends HttpServlet {

	    // service method
	    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	    	int sum=0;
	    	int i=Integer.parseInt(req.getParameter("num1"));
	    	int j=Integer.parseInt(req.getParameter("num2"));
	    	sum=i-j;
	        PrintWriter pw = res.getWriter();
	        pw.println("sub is "+sum);
	    }



}
