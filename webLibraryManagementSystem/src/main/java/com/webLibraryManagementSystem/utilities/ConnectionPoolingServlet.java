package com.webLibraryManagementSystem.utilities;

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

@WebServlet(urlPatterns = "/ConnectionPoolingServlet", loadOnStartup = 0)
public class ConnectionPoolingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataSource dataSource;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			InitialContext initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("lms");
			System.out.println("Connection Pool Created Succesfully...");
		} catch (NamingException | ClassNotFoundException e) {

			System.out.println("Connection Pool Creation Failed..." + e);
		}

	}

	public static DataSource getDataSource() {

		return ConnectionPoolingServlet.dataSource;
	}

	public ConnectionPoolingServlet() {
		super();
	}

	@Override
	public void destroy() {

		if (dataSource instanceof DataSource) {
			try {
				((Context) dataSource).close();
				System.out.println("Connection Pool Closed Successfully.");
			} catch (Exception e) {
				System.out.println("Error while closing connection pool: " + e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}