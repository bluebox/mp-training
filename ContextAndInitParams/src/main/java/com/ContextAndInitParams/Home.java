package com.ContextAndInitParams;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String url;
    private String user;
    private String password;
    private Connection conn;
    private PreparedStatement st;
    public void init() {
    	ServletContext context=getServletContext();
    	url=context.getInitParameter("DB");
    	user=context.getInitParameter("USER");
    	password=context.getInitParameter("PASSWORD");
      	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
      	try {
			conn=DriverManager.getConnection(url,user,password);
			st=conn.prepareStatement("insert into orders (customer_name) values(?)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
      	System.out.println("Connection is established");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
//		ServletContext a=getServletContext();
//		out.println(a.getInitParameter("DB"));
//		a.setAttribute("anand",22);
//		out.println(a.getAttribute("anand"));
//		Enumeration<String> attrNames = a.getAttributeNames();
//        while (attrNames.hasMoreElements()) {
//            String name = attrNames.nextElement();
//            Object value = a.getAttribute(name);
//            out.println(name+" "+value);
//        }
		out.println(url);
		out.println(user);
		out.println(password);
		String customer =request.getParameter("customer_name");
		try {
			st.setString(1, customer);
			int cnt=st.executeUpdate();
			if(cnt>0) {
				out.println("user is added");
			}
			else out.println("error while adding user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
