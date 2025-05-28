package HPS_Interface_Abstract_Class_Concept;

//import HPS_Interface_Abstract_Class_Concept.MedicineType;

public class Medicine {
	private String name;
	private MedicineType type;
	
	public Medicine(String name, MedicineType type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	public MedicineType getType()
	{
		return type;
	}
	
	@Override
	public String toString()
	{
		return name + "[" + type + "]";
	}
}
