package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParameterServlet
 */
@WebServlet(urlPatterns = "/initParameterServlet",initParams = {
		@WebInitParam(name="url" ,value="jdbc:mysql://localhost:3306/example"),
		@WebInitParam(name="user" ,value="root"),
		@WebInitParam(name="pass" ,value="root")
})
public class InitParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(config.getInitParameter("url"), config.getInitParameter("user"),
					config.getInitParameter("pass"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String f_name = request.getParameter("first_name");
		String l_name = request.getParameter("second_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		try (Statement pstmt = conn.createStatement()) {
			int insert = pstmt.executeUpdate(
					"insert into user values('" + f_name + "','" + l_name + "','" + email + "','" + pass + "')");

			if (insert > 0) {
				out.println(insert + " user(s) added successfully.");
			} else {
				out.println("User not added.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("Database error occurred.");
		}
	}

	@Override
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



//<servlet>
//
//<servlet-name>InitParameters</servlet-name>
//<servlet-class>com.vejas.examples.servlets.InitParameterServlet</servlet-class>
//
//<init-param>
//<param-name>dburl</param-name>
//<param-value>jdbc:mysql://localhost:3306/example</param-value>
//</init-param>
//
//<init-param>
//<param-name>user</param-name>
//<param-value>root</param-value>
//</init-param>
//
//<init-param>
//<param-name>pass</param-name>
//<param-value>root</param-value>
//</init-param>
//
//</servlet>
//
//<servlet-mapping>
//  <servlet-name>InitParameters</servlet-name>
// 	<url-pattern>/initParameterServlet</url-pattern>
//</servlet-mapping>
