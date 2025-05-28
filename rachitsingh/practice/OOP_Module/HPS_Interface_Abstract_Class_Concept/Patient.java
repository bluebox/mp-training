package HPS_Interface_Abstract_Class_Concept;

public class Patient {
	private String name;
	private int age;
	
	public Patient(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	public String getName() 
	{
	     return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	@Override
	public String toString()
	{
		return "Patient Details: " + "\n" 
				+ "Name: " + name + "\n" 
				+ "Age: " + age + "\n";
	}
}
