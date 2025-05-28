package learn2;

public class InheritanceChallenge {
	
	public static void main(String[] args) {
		
		SalariedEmployee anil = new SalariedEmployee();
		anil.annualSalary = 600000.00;
		anil.setName("Anil");
		anil.setBirthDate("1982");
		anil.setEmmployeeId(100001);
		anil.setEndDate("2022");
		anil.setAge(43);
		anil.setHireDate("2000");
		anil.isRetired = false;
		
		anil.terminate(anil.getEndDate());
		
		System.out.println(anil.getClass().getSimpleName());
		HourlyEmployee tom = new HourlyEmployee();
		tom.setHourlyPayRate(100.25);
		tom.setName("tom");
		tom.setAge(50);
		tom.setBirthDate("1975");
		tom.setEmmployeeId(5000003);
		tom.setEndDate("2024");
		tom.setHireDate("2005");
		
		System.out.println(tom.collectPay());
		tom.getDoublePay();
		System.out.println(tom.getClass().getSimpleName());
	}
}
