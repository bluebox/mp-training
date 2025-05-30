package oops.override;

public class Employee extends Manager {
	
	protected String ename,ejoining;
	protected int eage,eslary;
	
	public Employee(String name,String joining,int age,String ename,String ejoining,int eage,int esalary) {
		super(name,joining,age);
		this.ename=ename;
		this.ejoining=ejoining;
		this.eage=eage;
		this.eslary=esalary;
	}
	@override
	public void display() {
		System.out.println("Employee Details");
		System.out.println("Name : "+ename);
		System.out.println("Joined on : "+ejoining);
		System.out.println("Age : "+age);
		System.out.println("salary : "+eslary+"LPA");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager m1=new Manager("Mahesh","2019-03-12",45);
		m1.display();
		System.out.println("------------------------------------------------");
		Employee e1=new Employee("Mahesh","2019-03-12",45,"Renaiah","2025-05-21",21, 6);
		e1.display();
		System.out.println("------------------------------------------------");
		
		Manager m2=new Manager("Ganesh","2017-03-24",49);
		m2.display();
		
		System.out.println("------------------------------------------------");

		Manager m3=new Employee("Mahesh","2019-03-12",45,"Sairaj","2025-04-21",22, 5);
		m3.display();
		System.out.println("------------------------------------------------");

		try {
			Employee e2=(Employee) new Manager("Jagan","2023-07-12",38);
//			Employee e2=new Manager();
			e2.display();
		}
		catch(Exception e) {
			System.out.println("it gives error");
		}
	}

}

