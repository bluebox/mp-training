package DrugManageUsingConstructorChaining;

public class Main {
	public static void main(String [] args)
	{
		PrescriptionDrug drug1 = new PrescriptionDrug("Amoxicillin 500 mg", "AM123", "07/2027", "Pfizer Inc.");
		System.out.println(drug1);
		System.out.println();
		PrescriptionDrug drug2 = new PrescriptionDrug("Paracetamol 650 mg", "PM323", "09/2028");
		System.out.println(drug2);
	}
}
