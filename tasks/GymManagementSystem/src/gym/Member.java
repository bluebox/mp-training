package gym;

public class Member extends Person{
	
	static long idGenerator=100;
	long memId;
	public long getMemberId() {
		return memId;
	}
	MemberShipPlan memberShip;
	
	
	public Member(String name, int age) {
		super(name, age);
		memId=idGenerator++;
	}
	public void addPlan(PlanDetails plan,int duration)
	{
		this.memberShip= new MemberShipPlan(plan,duration);
	}
	@Override
	public void getDetails() {
		System.out.println( "Member [memId=" + memId
				+ "name=" + name + ", age=" + age + "]");
		memberShip.getPlanDetails();
	}

	
	
	
	
	


}
