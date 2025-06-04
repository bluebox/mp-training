package oops.abstraction;

public class HrDept extends Company {
	protected String hrName;
	protected String hrJoinedDate;
	
	public HrDept(String companyName,String locationOfCompany,String hrName,String hrJoinedDate) {
		super(companyName,locationOfCompany);
		this.hrName=hrName;
		this.hrJoinedDate=hrJoinedDate;
	}
	

	public void showData() {
		
		System.out.println("	HR Department Details ");
		System.out.println("------------------------------------------------");

		System.out.println("Company : "+ companyName);
		System.out.println("Location : "+locationOfCompany);
		System.out.println("HR Name : "+hrName);
		System.out.println("HR Joined on : "+hrJoinedDate);
		
		System.out.println("------------------------------------------------");

	}

}
