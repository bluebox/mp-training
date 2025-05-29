package Advanced_Casting_Project;

public class HospitalStaffMain {
	public static void main(String [] args)
	{
		Doctor doc1 = new Doctor("Dr. Pawan Gupta");
		Nurse nurse1 = new Nurse("Nurse Vimla");
		var chemist1 = new Pharmacist("Chemist Mr. Khanna");
		
		// Implicit Upcasting
		Staff generalStaff1 = doc1;
		Staff generalStaff2 = nurse1;
		
		generalStaff1.task();
		generalStaff2.task();
		
		// Explicit Downcasting
		if(generalStaff1 instanceof Doctor)
		{
			Doctor doc2 = (Doctor)generalStaff1; // I have initialized a new Doctor type object, but making it refer to a Staff class (parent) object by downcasting it to Doctor type
			doc2.prescribingMedicine();
		}
		
		if(generalStaff2 instanceof Nurse)
		{
			Nurse nurse2 = (Nurse)generalStaff2; // I have initialized a new Nurse type object, but making it refer to a Staff class (parent) object by downcasting it to Nurse type
			nurse2.helpingDoctor();
		}
		
		Staff generalStaff3 = chemist1;
		generalStaff3.task();
		
		if(generalStaff3 instanceof Pharmacist)
		{
			Pharmacist chemist2 = (Pharmacist)generalStaff3;
			chemist2.providingPrescriptions();
		}
	}
}

class Staff {
	protected String name;
	public Staff(String name)
	{
		this.name = name;
	}
	public void task()
	{
		System.out.println(name + " is performing general hospital task.");
	}
}

class Doctor extends Staff{
	public Doctor(String name)
	{
		super(name);
	}
	public void prescribingMedicine()
	{
		System.out.println(name + " is prescribing medicine to the patient.");
	}
}

class Nurse extends Staff{
	public Nurse(String name)
	{
		super(name);
	}
	public void helpingDoctor()
	{
		System.out.println(name + " is helping the doctor.");
	}
}

class Pharmacist extends Staff
{
	public Pharmacist(String name)
	{
		super(name);
	}
	
	public void providingPrescriptions()
	{
		System.out.println(name + "is providing the prescribed medicines to the patient.");
	}
}
