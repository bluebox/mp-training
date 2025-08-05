package com.registration.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.registration.dao.EmployeeDao;
import com.registration.model.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 @WebServlet("/register")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
private EmployeeDao ed=new EmployeeDao();
protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
{
	res.getWriter().append("served at :").append(req.getContextPath());
	RequestDispatcher dph=req.getRequestDispatcher("employeeregister.jsp");
	dph.forward(req, res);
	
	}
protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
{  
	
	
	
	String firstName=req.getParameter("FirstName");
	String lastName=req.getParameter("lastName");
	String userName=req.getParameter("userName");
	String password=req.getParameter("password");
	String address=req.getParameter("address");
	String contact=req.getParameter("contact");
	
	Employee emp=new Employee();
	emp.setFirstName(firstName);
	emp.setLastName(lastName);
	emp.setUserName(userName);
	emp.setPassword(password);
	emp.setAddress(address);
	emp.setContact(contact);
	
	try {
		ed.registrationEmployee(emp);
		List<Employee> employees=ed.getAllEmployees();
//		for (Employee i:employees)
//		{
//			System.out.println(i.getFirstName());
//		}
		req.setAttribute("empList", employees);
		
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	RequestDispatcher dph=req.getRequestDispatcher("employeeDetails.jsp");
	dph.forward(req, res);
	
	
}
}
