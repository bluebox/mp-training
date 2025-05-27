package DrugManageUsingConstructorChaining;

public class Drug {
	public String drugName;
	public String drugBatchNumber;
	
	public Drug(String name, String batchNumber)
	{
		drugName = name;
		drugBatchNumber = batchNumber;
		
		System.out.println("Basic Drug Info has been initialized (Parent class constructor called).");
	}
}
