package annonymous;

public class Main {
	
	public static void main(String[] args) {
		Example e = new Example();
		Example.EmployeeObjects newEmployee = e.new EmployeeObjects("madhav","nlv") {
			
			@Override
			public boolean addEmployee() {
				Employee emp = new Employee(getFirstName(), getLastName(), getDate());
				e.getEmployees().add(emp);
				return true;
			}
		};
		newEmployee.addEmployee();
		System.out.println(e.getEmployees());
	}

}
