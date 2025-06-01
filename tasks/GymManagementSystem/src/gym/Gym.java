package gym;

import java.util.HashMap;

class Gym {

	HashMap<Long, Member> members;
	static String name = "MEDPLUS GYM";
	static String description = "Excuses don’t burn calories.";
	

	public Gym() {
		super();
		members = new HashMap<>();
		System.out.println("Welcome to " + name);
		System.out.println("**** " + description+" ****");
		System.out.println("-".repeat(20));
	}

	public void addMember(Member member) {
		members.put(member.getMemberId(), member);

	}

	public void deleteUser(long id) {
		if(members.containsKey(id))
		{
			members.remove(id);
			System.out.println("User deleted with user id "+id);
			
		}
		else
		{
			System.out.println("User Not Found");
		}
	}

	public void printDetails() {
		members.forEach((l, member) -> {
			System.out.println();
			member.getDetails();

		});
	}

	public Member getMember(long id) {
		if (members.containsKey(id)) {
			return members.get(id);
		} else {

			return null;
		}

	}

}
