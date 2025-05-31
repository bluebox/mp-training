package singleinheritance;

public class College {
	private String name;
	private String location;
	private String principal;

	public College(String name,String location,String principal) {
		this.name = name;
		this.location=location;
		this.principal=principal;
	}
	public void collegeInfo()
	{
		System.out.println("College name :- "+name+"\nLocation:- "
				+location+"\n principal:- "+principal);
		
	}
	

}
