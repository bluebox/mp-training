

public class Member extends Person {
	private long memberId;

	private MemberShip membership=null;

	private Trainer trainer=null;

	public Member(long memberId, String name, int age, String gender) {
		super(name, age, Gender.valueOf(gender));
		this.memberId = memberId;
	}

	@Override
	public void showDetails() {
		System.out.println("The Member Details are :");
		System.out.println("Member ID : " + this.getMemberId());
		System.out.println("Name  : " + this.getName());
		System.out.println("Age  : " + this.getAge());
		System.out.println("-".repeat(30));
		if (membership != null) {
			membership.getMemberShipDetails();
		} else {
			System.out.println("No Plan is assigned");
		}

		System.out.println("-".repeat(30));

		if (trainer != null) {
			trainer.showDetails();
		} else {
			System.out.println("No Trainer is assigned");
		}
	}
    public void setName(String name) {
    	this.name=name;
    }
    public void setAge(int age) {
    	this.age=age;
    }
	public boolean isMemberShip() {
		return membership != null;
	}
	public boolean isTrainer() {
		return trainer != null;
	}
	
    public String getPlan() {
    	if(membership!=null) {
    		return membership.getPlan();
    	}
    	else {
    		return "No Plan is Assigned";
    	}
    }
    
    public String getTrainer() {
    	if(trainer!=null) {
    		return trainer.getName();
    	}
    	else {
    		return "No Trainer is Assigned";
    	}
    }
	public void setMemberShip(String plan) {
		membership = new MemberShip(Plan.valueOf(plan));
	}
	
	public void setTrainer(Trainer trainer) {
		this.trainer=trainer;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getMemberId() {
		return "%s".formatted(memberId);
	}

}
