package Case_Study;

public class Member extends Person {
	private long memberId;
	private MemberShip membership;
	public Member(String name,int age,String gender,long memberId){
		super(name,age,Gender.valueOf(gender.toUpperCase()));
		this.memberId=memberId;
	}
	@Override
	public void showDetails() {
		System.out.println("The Member Details are :");
		System.out.println("Member ID : "+this.getMemberId());
		System.out.println("Name  : "+this.getName());
		System.out.println("Age  : "+this.getAge());
		System.out.print("The Plan Details are : ");
		if(membership!=null) {
			System.out.println();
			membership.getMemberShipDetails();
		}
		else {
			System.out.println("No Plan is assigned");
		}
	}
	
	public void setMemberShip(String plan) {
		membership=new MemberShip(Plan.valueOf(plan.toUpperCase()));
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
	public String getMemberId() {
		return " %s ".formatted(memberId);
	}

}
