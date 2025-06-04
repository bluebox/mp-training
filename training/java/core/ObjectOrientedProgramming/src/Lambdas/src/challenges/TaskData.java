package challenges;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskData {
	
	private List<EmployeeDetails> employees;
	
	private void loadEmployeeDetails()
	{
		
		try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("/details")))
		{
			employees = (ArrayList<EmployeeDetails>)inputStream.readObject();
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}catch(ClassNotFoundException cfe)
		{
			cfe.printStackTrace();
		}
		 
	}
	
	
	public void saveEmployeeDetails()
	{
		try(ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream("/details")))
		{
			objectStream.writeObject(employees);
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	public TaskData()
	{
		employees = new ArrayList<EmployeeDetails>();
		loadEmployeeDetails();
	}
	
	
	public void close()
	{
		saveEmployeeDetails();
	}
	
	public void createEmployee(String id, String name, int age, String designation, String phoneNumber, String mail)
	{
		EmployeeDetails employee = new EmployeeDetails(id, name, age, designation,mail, phoneNumber);
		employees.add(employee);
		
	}
	public void deleteEmployee(String id)
	{
		for(var emp:employees)
		{
			if(emp.getId().equals(id))
			{
				employees.remove(emp);
				break;
			}
		}
	}
	
	public void display()
	{
		for(var emp:employees)
		{
			System.out.println("Employee ID : "+emp.getId());
			System.out.println("Employee Name : "+emp.getName());
			System.out.println("Employee age : "+emp.getAge());
			System.out.println("Employee designation : "+emp.getDesignation());
			System.out.println("Employee Contact details : \nEmployee Phone No : "+emp.getContact().getPhoneNumber());
			System.out.println("Employee email id : "+emp.getContact().getEmailId());
		}
	}
	
}
