package challenge;

import java.util.ArrayList;

public class Resource {
	public class Employees
	{
		String name;
		public Employees()
		{
			
		}
		public Employees(String name)
		{
			this.name = name;
		}
	}
	
	public ArrayList<Employees> employees = null;
	public int counter;
	public Resource()
	{
		employees = new ArrayList<Resource.Employees>();
		counter = 0;
	}
	public synchronized void incCounter()
	{
		counter++;
	}
	public synchronized void addEmployees(Employees emp)
	{
		employees.add(emp);
		incCounter();
	}
	public synchronized void decCounter()
	{
		if(counter == 0)
		{
			return;
		}
		counter--;
	}
	public synchronized void removeEmployee()
	{
		if(counter <= 0)
		{
			System.out.println("No employees present");
			return;
		}
		employees.remove(counter-1);
		decCounter();
		
	}

}
