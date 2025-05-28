package HPS_Interface_Abstract_Class_Concept;

// The class Cardiologist must implement the inherited abstract method MedicinePrescriber.prescribeMedicine(Patient, Medicine)

public class Cardiologist extends Doctor{
	public Cardiologist(String name)
	{
		super(name, "Cardiologist");
	}
	public String getName()
	{
		return name;
	}
	public void diagnose()
	{
		System.out.println("Currently heart-related issue is being diagonsed....");
	}
	public void prescribeMedicine(Patient pat, Medicine med)
	{
		System.out.println("Cardiologist " + "Dr. " + name + " has prescribed " + med.getName() + " to patient: " + pat.getName());
	}
}
