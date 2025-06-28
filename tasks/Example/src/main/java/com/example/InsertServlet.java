package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		var cnfg=getServletConfig();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String Pet_name=request.getParameter("pet_name");
		int Pet_id=Integer.parseInt(request.getParameter("pet_id"));
		int pet_age=Integer.parseInt(request.getParameter("pet_age"));
		String pet_breed=request.getParameter("breed");
		var connector=new MysqlDataSource();
		connector.setServerName("localhost");
		connector.setPort(3306);
		connector.setDatabaseName(cnfg.getInitParameter("DatabaseName"));
		connector.setUser(cnfg.getInitParameter("UserName"));
		connector.setPassword(cnfg.getInitParameter("Password"));
//		connector.setDatabaseName("sys");
//		connector.setUser("Kanishka");
//		connector.setPassword("Kanishka123#");
		
		String sql_query=String.format("insert into Pets(P_Name,ID,age,Breed) values('%s',%d,%d,'%s');",Pet_name,Pet_id,pet_age,pet_breed);
		try(Connection conn=connector.getConnection()){
			Statement new_statement=conn.createStatement();
			int res=new_statement.executeUpdate(sql_query);
			out.println("Status of insertion"+res);
			
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
