package com.ContextAndInitParams;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(
//	    urlPatterns = "/initExample",
//	    initParams = {
//	        @WebInitParam(name = "USER", value = "Anand"),
//	        @WebInitParam(name = "Password", value = "1925112816@Aa")
//          @WbInitParam(name="DB",value="jdbc:mysql://localhost:3306/test")
//	    }
//	)
public class InitExample extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String url;
    private String user;
    private String password;
    public void init() {
    	ServletConfig config=getServletConfig();
    	url=config.getInitParameter("DB");
    	user=config.getInitParameter("USER");
    	password=config.getInitParameter("Password");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        ServletConfig b=getServletConfig();
//        out.println(b.getInitParameter("USER"));
//        out.println(b.getInitParameter("DB"));
//        out.println(b.getInitParameter("Password"));
        out.println("<h1>"+url+"</h1>");
    }
}
