package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class SumServlet
 */
@WebServlet("/SumServlet")
public class SumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		int num1=Integer.parseInt(request.getParameter("number_1"));
//		int num_2=Integer.parseInt(request.getParameter("number_2"));
		response.setContentType("text/html");
		PrintWriter ans_write=response.getWriter();
//		int sum=num1+num_2;
//		ans.println("<html><body>");
//		ans.println("<p>");
//		ans.println("The answer is this"+sum);
//		ans.println("</p></body></html>");
		
		var connector=new MysqlDataSource();
		connector.setServerName("localhost");
		connector.setUser("Kanishka");
		connector.setPort(3306);
		connector.setPassword("Kanishka123#");
		connector.setDatabaseName("sys");
		String exe_query=String.format("Update Pets set ID=7 where ID=7 or ID=2");
		try(Connection conn=connector.getConnection()){
			Statement new_statement=conn.createStatement();
			int ans_2=new_statement.executeUpdate(exe_query);
			ans_write.println(ans_2);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
