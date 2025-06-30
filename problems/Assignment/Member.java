package Assignment;

public class Member extends Person{
	private  int memeberShipId;
	private  MemberShipPlan plan;
	  
	  public Member(int age,String name) {
		  super(age,name);
	  }
	  
	  public Member(int memeber,int age,String name) {
		  this(memeber,null,age,name);
	  }
	  
	  public Member(int memeberShipId,MemberShipPlan plan,int age,String name) {
		  super(age,name);
		  this.memeberShipId=memeberShipId;
		  this.plan=plan;
	  }

	public int getMemeberShipId() {
		return memeberShipId;
	}

	public void setMemeberShipId(int memeberShipId) {
		this.memeberShipId = memeberShipId;
	}

	public MemberShipPlan getPlan() {
		return plan;
	}

	public void setPlan(MemberShipPlan plan) {
		this.plan = plan;
	}
	  
}
