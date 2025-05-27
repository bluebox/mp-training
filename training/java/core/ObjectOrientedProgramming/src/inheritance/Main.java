package inheritance;

public class Main {

	public static void main(String[] args) {
		SalariedEmployee emp1 = new SalariedEmployee("Madhav","10/05/2004",null,25010,"21-05-2024", 600000, false);
		System.out.println(emp1);
		emp1.retire();
		System.out.println(emp1);
		
		HourlyEmployee emp2 = new HourlyEmployee("Madhav", "11-05-2004", null, 10, "10-05-2024", 1000);
		emp2.collectPay();
		System.out.println(emp2);
		System.out.println(emp2.getDoublePay());
	}

}
