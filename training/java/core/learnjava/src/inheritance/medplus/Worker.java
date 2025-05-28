package inheritance.medplus;

public class Worker {

	
	public static void main(String[] args) {
		HourlyEmployee he = new HourlyEmployee();
		
		System.out.println("The age of the Employee : "+he.getAge());
		
		System.out.println("The collected pay is : "+he.colletPay());
		he.getDoublePay();
		
		salariedEmployee se = new salariedEmployee();
		se.terminate(se.endDate);
		se.retire();
		
	}

}

class worker{
	 String name = "Saketh Maryala";
	 String birthDate = "19/07/2002";
	 String endDate = "21/05/2028";
	
	public  int getAge() {
		return 2025 - Integer.parseInt(birthDate.substring(7));
		
	}
	public  double colletPay() {
		return 10000000.000;
		
	}
	
	public  void terminate(String endDate) {
		System.out.println("You are terminating on "+endDate);
	}
}

class employee extends worker{
	long employeeid = 894375;
	String hireDate = "21/05/2025";
}
class HourlyEmployee extends employee{
	double hourlyPayRate = 100.00;
	
	public void getDoublePay() {
		System.out.println("The Employe paid hourly : "+this.hourlyPayRate);
	}
}
class salariedEmployee extends employee{
	double annualsalary = 700000.00;
	boolean isRetired = false;
	
	public void retire() {
		System.out.println("The employee retired : "+this.isRetired);
	}
}


