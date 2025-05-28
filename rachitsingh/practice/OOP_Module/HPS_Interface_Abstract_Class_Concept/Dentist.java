package HPS_Interface_Abstract_Class_Concept;

public class Dentist extends Doctor{
	public Dentist(String name)
	{
		super(name, "Dentist");
	}
	public String getName()
	{
		return name;
	}
	public void diagnose()
	{
		System.out.println("Dental issue is being examined....");
	}
	public void prescribeMedicine(Patient pat, Medicine med)
	{
		System.out.println("Dentist " + name + " has prescribed " + med.getName() + " to patient: " + pat.getName());
	}
}
