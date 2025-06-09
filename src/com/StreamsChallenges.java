
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsChallenges {
	public static void main(String[] args) {
		
		List<EmployeeDataForStream> employees = getEmployeeData();
//		printEmployeeData(employees);
		employees.stream()
		.filter(e->e.getSalary()/12 > 50000)
		.filter(e->e.getDepartment().equals("IT"))
		.sorted((e1,e2)-> e2.getSalary() - e1.getSalary())
		.map(EmployeeDataForStream::getName)
		.collect(Collectors.toList())
		.forEach(System.out::println);
		
//		List<EmployeeDataForStream> listEmployees = streamOfEmp.collect(Collectors.toList());
//		listEmployees.forEach(System.out::println);
//		.forEach(System.out::println);
	}
	
	
	public static ArrayList<EmployeeDataForStream> getEmployeeData() {
		ArrayList<EmployeeDataForStream> employees = new ArrayList<>();
		String arr[] = new String[] {"Mourya","Surya","Gayathri","Namratha","yaswini","navya","david","prince","yaswanth",
				"rolson","siddhu"};
		String depts[] = new String[] {"IT","HR","Sales","Data science","Marketing","Development","Testing","Fullstack"};
		Random random = new Random();
		for(int i=0;i<100;i++) {
			employees.add(new EmployeeDataForStream(arr[random.nextInt(0, arr.length)], random.nextInt(20,40),
					depts[random.nextInt(0, depts.length)], random.nextInt(4_00_000, 12_00_000)));
		}
		return employees;
	}
	
	public static void printEmployeeData(List<EmployeeDataForStream> employees) {
		System.out.println("The employees data is as follows: ");
		employees.stream()
		.forEach(System.out::println);
	}
}
