package oops.overLoading;

public class MethodOverLoading {
	
//	int totalSalary;
	public void MethodOverLoading(){
		System.out.println("Default constructer called....");
	}
	public void MethodOverLoading(String name,int id){
		System.out.println("Name : "+name+" and id : "+id);
	}
	public void MethodOverLoading(int salary,int bonus,boolean getHike){
		System.out.println("Current Salary : "+salary+" is eligible to bonus : "+getHike);
//		if(getHike) {
//			totalSalary=salary+((salary*bonus)/100);
//		}
		int totalSalary=getHike?salary+((salary*bonus)/100):salary;
		System.out.println("Th new salary : "+totalSalary);
	}
	public void displayInfo() {
	}
	public static void main(String[] args) {
		MethodOverLoading mt=new MethodOverLoading();
		System.out.println("The employee Details :");
		mt.MethodOverLoading("Renu",247);
		mt.MethodOverLoading(45000,15,true);
	}	
}
