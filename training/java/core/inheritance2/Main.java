
public class Main {

	public static void main(String[] args) {
		Worker worker1 =new Worker();
		worker1.setName("w1");
		System.out.println("Name of worker1 is "+worker1.getName());
		worker1.setBirthDate("10/01/1990");
		worker1.setEndDate("26/05/2025");
		System.out.println("Age of the worker1 is "+worker1.getAge());
		worker1.setAnnualSalary(10000.00);
		System.out.println("Annual Salary of worker1 is "+worker1.getAnnualSalary());
		worker1.setRetired(false);
		System.out.println("\nIs worker1 retired? - "+ worker1.isRetired());
		System.out.println("Let's retire worker1");
		worker1.retire();
		System.out.println("Is worker1 retired? - "+ worker1.isRetired());

	}

}
