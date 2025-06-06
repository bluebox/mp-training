

public class Trainer extends Person {
	private long trainerId;
	private PlanConstants type;

	public Trainer(long trainerId, String type, String name, int age, String gender) {
		super(name, age, Gender.valueOf(gender));
		this.type = PlanConstants.valueOf(type);
		this.trainerId = trainerId;
	}
	
	public void setTrainerDomain(PlanConstants type) {
		this.type=type;
	}
    public String getDomain() {
    	return "%s".formatted(type);
    }
	public String getAge() {
		return "%s".formatted(age);
	}

	public String getTrainerId() {
		return "%s".formatted(trainerId);
	}

	public String getGender() {
		return "%s".formatted(gender);
	}

	public String getName() {
		return name;
	}

	public String getTrainerType() {
		return type.toString();
	}

	@Override
	void showDetails() {
		System.out.println("The Trainer Details are :");
		System.out.println("Trainer Domain : " + this.getTrainerType());
		System.out.println("Trainer Id : " + this.getTrainerId());
		System.out.println("Trainer Name : " + this.getName());
		System.out.println("Age : " + this.getAge());
		System.out.println("Gender : " + this.getGender());
	}

}
