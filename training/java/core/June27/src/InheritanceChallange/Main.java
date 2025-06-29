package InheritanceChallange;

public class Main {

	public static void main(String[] args) {
		SalariedEmployee employee = new SalariedEmployee("ram","1999","2028",123,"2020",400000,false);
		System.out.println("Employee Details :\n" + employee);
		employee.getAge();
		employee.collectPay();
		System.out.println("employee Annual salary is "+employee.getAnnualSalary());
		if(employee.retire())
		{
			System.out.println(employee.getName()+" is Retired");
		}
		else {
			System.out.println(employee.getName()+" is not Retired");
		}
		
		HourlyEmployee PartTimeEmployee = new HourlyEmployee("sam","1990","2026",1289,"2023",2500);
		System.out.println(PartTimeEmployee);
		System.out.println("DoublePay is "+PartTimeEmployee.getDoublePay());
	}

}
