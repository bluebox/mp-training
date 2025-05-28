package MultiInherit;

public class Main {
	public static void main(String[] args) {
		
	SalariedEmployee emp=new SalariedEmployee("Vivek","10-09-1987","11-11-2017",12345l,"23-09-2001",900000,true);
	HourlyEmployee emp2=new HourlyEmployee("Arjun","17-08-1985","18-10-2005",32415l,"08-09-2003",200);
	System.out.println(emp.getAge() +" "+ emp.collectPay()+" ");
	emp.retire();
	System.out.println("The hourly salary for hour employee"+ emp2.getDoublePay());

}
}