package gym;

public class Member extends Person{
	
	static long idGenerator=100;
	long memId;
	boolean isSubscribed;
	MemberShipPlan memberShip;
	
	
	public Member(String name, int age) {
		super(name, age);
		memId=idGenerator++;
		this.isSubscribed=false;
	}
	
	public long getMemberId() {
		return memId;
	}
	
	public void addPlan(PlanDetails plan,int duration)
	{
		isSubscribed=true;
		this.memberShip= new MemberShipPlan(plan,duration);
	}
	@Override
	public void getDetails() {
		System.out.println( "Member [memId=" + memId
				+ "name=" + name + ", age=" + age + "]");
		if(isSubscribed)
		{
			memberShip.getPlanDetails();
			
		}
		else
		{
			System.out.println("Not Subscribed under any plan");
		}
	}

	
	
	
	
	


}
