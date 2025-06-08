package day12.inheritencechallenge;

public class InheritenceChallenge {

	
	public static void main(String args[]) {
		SalariedEmployee s1=new SalariedEmployee(5_43_236,false,1,"21-05-2025","Krishna prasad","27-05-2001","21-07-2027");
		System.out.println("age of the employee is "+s1.getAge());
		System.out.println("experience in company is "+ s1.getEmplymentDuration());
		s1.retire();
	}
}
