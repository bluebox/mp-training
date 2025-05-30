package inheritance;

public class InheritenceChallenge {
public static void main(String [] args) {
	SalariedEmployee salaried=new SalariedEmployee("Niranjan", "2003-09-03", "2025-05-09", 12321, "2023-03-09" , 43256.89);
	System.out.println(salaried.getAge());
	System.out.println(salaried.collectPay());
	HourlyEmployee hourly =  new HourlyEmployee("Niranjan", "2007-09-03", "2025-05-09", 12321, "2023-03-09", 400.23 );
	System.out.println(hourly.getAge());
	System.out.println(hourly.collectPay());
	hourly.getDoublePay();
	
}
}
