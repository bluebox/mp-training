package gym;

import java.util.HashMap;

class Gym {
	
	HashMap<Long,Member> members;
	static String name="MEDPLUS GYM";
	static String description="Prevation is better than cure";
	
	
	public Gym() {
		super();
		members=new HashMap<>();
		System.out.println("Welcome to "+name);
		System.out.println("**** " +description);
		}

	
	public void addMember(Member member)
	{
		members.put(member.getMemberId(),member);
		
	}
	public void deleteUser(long id)
	{
		members.remove(id);
	}
	public void printDetails()
	{
//		System.out.println("Welcome to "+name);
//		System.out.println("**** " +description);
		System.out.println("-".repeat(50));
		members.forEach((l,member)->{
			System.out.println();
			member.getDetails();
			
			
		});
	}
	public Member getMember(long id)
	{
		if(members.containsKey(id))
		{
			return members.get(id);
		}
		else
		{
			
			return null;
		}
		
	}


}
