package HPS_Interface_Abstract_Class_Concept;

public class Main {
	public static void main(String [] args)
	{
		// Initializing patient objects
		Patient p1 = new Patient("Ravi Mishra", 47);
		Patient p2 = new Patient("Munshi Premchand", 65);
		
		//Initializing medicine objects
		Medicine m1 = new Medicine("Cetrizine", MedicineType.TABLET);
		Medicine m2 = new Medicine("ORS", MedicineType.SYRUP);
		
		//Initializing doctor objects
		Cardiologist cardiologist = new Cardiologist("Anoop Awasthi");
		Dentist dentist = new Dentist("Sunil Bansal");
		
		//Initializing prescription manager object
		PrescriptionManager pm = new PrescriptionManager();
		
		cardiologist.diagnose();
		cardiologist.prescribeMedicine(p1, m1);
		pm.addNewPrescription("Dr. " + cardiologist.getName() + " --> " + p1.getName() + " : " + m1.getName() + " [" + m1.getType() + "]");
		
		System.out.println();
		dentist.diagnose();
		dentist.prescribeMedicine(p2, m2);
		pm.addNewPrescription("Dr. " + dentist.getName() + " --> " + p2.getName() + " : " + m2.getName() + " [" + m2.getType() + "]");
		
		System.out.println();
		System.out.println("Reviewing the prescription records as of now: ");
		pm.displayAllPrescriptions();
		
	}
}
