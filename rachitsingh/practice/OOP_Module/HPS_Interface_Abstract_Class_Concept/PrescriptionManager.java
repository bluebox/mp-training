package HPS_Interface_Abstract_Class_Concept;
import java.util.*;

public class PrescriptionManager {
	private List<String>prescriptionList = new ArrayList<String>();
	
	public void addNewPrescription(String prescription)
	{
		prescriptionList.add(prescription);
	}
	
	public void displayAllPrescriptions()
	{
		for(int i = 0; i < prescriptionList.size(); i++)
		{
			System.out.println(prescriptionList.get(i));
		}
	}
}
