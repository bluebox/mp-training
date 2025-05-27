package DrugManageUsingConstructorChaining;

public class PrescriptionDrug extends Drug{
	private String expiryDate;
	private String company;
	
	public PrescriptionDrug(String name, String batchNumber, String expiryDate)
	{
		
		this(name, batchNumber, expiryDate, "Unknown Company");
		System.out.println("This constructor is for the prescribed drug whose manufacturing company is unknown.");
	}
	public PrescriptionDrug(String name, String batchNumber, String expiryDate, String company)
	{
		super(name, batchNumber);
		this.expiryDate = expiryDate;
		this.company = company;
		
		System.out.println("This constructor is for the prescribed drug with full information available.");
	}
	@Override
	public String toString() {
        return "Drug Name: " + drugName +
               "\nBatch Number: " + drugBatchNumber +
               "\nExpiry Date: " + expiryDate +
               "\nCompany: " + company;
    }
}
