package com.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.registration.model.Employee;




public class EmployeeDao {
	
 public void registrationEmployee(Employee employee) throws ClassNotFoundException, SQLException
 {
	
	 String qry="INSERT INTO employee"+"(first_name,last_name,username,password,address,contact) VALUES"+"(?,?,?,?,?,?);";
	
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 //System.out.println("going to insert");
	 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets", "root", "Gopi@2507");
	 PreparedStatement ps=conn.prepareStatement(qry);

	 ps.setString(1, employee.getFirstName());
	 ps.setString(2, employee.getLastName());
	 ps.setString(3, employee.getUserName());
	 ps.setString(4, employee.getPassword());
	 ps.setString(5,employee.getAddress());
	 ps.setString(6, employee.getContact());
	 
	 ps.executeUpdate();
	
	 }
 
 public List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException {
     List<Employee> employeeList = new ArrayList<>();

     String qry = "SELECT * FROM employee";
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets", "root", "Gopi@2507");
     PreparedStatement ps = conn.prepareStatement(qry);
     ResultSet rs = ps.executeQuery();
   //System.out.println("gopi");
     while (rs.next()) {
         Employee emp = new Employee();
         emp.setId(rs.getInt("id"));
         emp.setFirstName(rs.getString("first_name"));
         emp.setLastName(rs.getString("last_name"));
         emp.setUserName(rs.getString("username"));
         emp.setPassword(rs.getString("password"));
         emp.setAddress(rs.getString("address"));
         emp.setContact(rs.getString("contact"));

         employeeList.add(emp);
     }
     

     rs.close();
     ps.close();
     conn.close();

     return employeeList;
 }
}
