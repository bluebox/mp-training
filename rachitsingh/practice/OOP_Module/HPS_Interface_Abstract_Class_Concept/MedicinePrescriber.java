package HPS_Interface_Abstract_Class_Concept;


// Every type of Doctor must implement the prescribeMedicine() method of this interface
public interface MedicinePrescriber {
	
	void prescribeMedicine(Patient pat, Medicine med);
}
