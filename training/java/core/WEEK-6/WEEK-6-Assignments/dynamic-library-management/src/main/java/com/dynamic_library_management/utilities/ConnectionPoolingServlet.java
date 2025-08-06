package com.dynamic_library_management.utilities;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionPoolingServlet
 */
@WebServlet(urlPatterns = "/ConnectionPoolingServlet", loadOnStartup = 0)
public class ConnectionPoolingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataSource dataSource;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			InitialContext initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("lms");
			System.out.println("Connection Pool Created Succesfully...");
		} catch (NamingException | ClassNotFoundException e) {

			System.out.println("Connection Pool Creation Failed...");
			e.printStackTrace();
		}

	}

	public static DataSource getDataSource() {
		return ConnectionPoolingServlet.dataSource;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionPoolingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
