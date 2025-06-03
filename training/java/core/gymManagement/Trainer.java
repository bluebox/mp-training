package gymManagement;

import java.util.ArrayList;

public class Trainer extends Person {
	private static int idCounter=1;
	private final int trainerId;
	private String expertise;
	private ArrayList<Trainee> trainees;
	
	public Trainer(String name, int age, String expertise) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.trainerId=idCounter++;
		this.expertise=expertise;
		this.trainees=new ArrayList<>();
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("Trainer ID: " + trainerId);
		System.out.println("Trainer Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Expertise: " + expertise);
        System.out.println("Number of Trainees: " + trainees.size());
        viewTrainees();
        
	}
	public int getTrainerId()
	{
		return trainerId;
	}
	public void assignTrainee(Trainee trainee) {
        if (!trainees.contains(trainee)) {
            trainees.add(trainee);
            trainee.assignTrainer(this);
        }
    }
	public void viewTrainees()
	{
		System.out.println("Trainees Under "+getName()+": ");
		for(Trainee trainee:trainees)
		{
			System.out.println(trainee.getName());
		}
	}

}
