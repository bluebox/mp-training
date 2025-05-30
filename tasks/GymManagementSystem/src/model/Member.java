package model;

public class Member extends Person{
	
	private String memberId;
	private Subscription subscribe;

	public Member(String name, int age, String gender, String address, String memberId,Subscription subscribe) {
		super(name, age, gender, address);
		this.memberId = memberId;
		this.subscribe = subscribe;
	}
	
	
	
	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public Subscription getSubscribe() {
		return subscribe;
	}



	public void setSubscribe(Subscription subscribe) {
		this.subscribe = subscribe;
	}



	/*
	 * needs proper definition.
	 * */
	@Override
	public void getPersonalDetails() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
