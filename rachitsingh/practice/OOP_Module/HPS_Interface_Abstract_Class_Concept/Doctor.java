package HPS_Interface_Abstract_Class_Concept;

abstract class Doctor implements MedicinePrescriber{
	protected String name;
	protected String specializedAs;
	
	public Doctor(String name, String specializedAs)
	{
		this.name = name;
		this.specializedAs = specializedAs;
	}
	
	// This method is forced to be defined in the sub-classes which extends this abstract class
	public abstract void diagnose();
}
