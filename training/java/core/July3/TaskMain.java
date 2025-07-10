package July3;

import java.util.Scanner;

public class TaskMain {
	public static void main(String[] args) {
		
		TaskData data = new TaskData();
		
		data.add(new Task("Infrastructure", "Logging", Priority.HIGH));
		data.add(new Task("Infrastructure", "DB Access", Priority.MEDIUM));
		data.add(new Task("Infrastructure", "Security", Priority.HIGH));
		data.add(new Task("Infrastructure", "Password Policy", Priority.MEDIUM));		
		data.add(new Task("Data Design","Task Table",Priority.MEDIUM));
		data.add(new Task("Data Design","Employee Table",Priority.MEDIUM));
		data.add(new Task("Data Design","Cross Reference",Priority.HIGH));
		data.add(new Task("Data Design","Encryption Policy",Priority.HIGH));		
		data.add(new Task("Data Access","Write Views",Priority.LOW));
		data.add(new Task("Data Access","Set Up Users",Priority.LOW));
		data.add(new Task("Data Access","Set Up Access",Priority.LOW));
		
		data.add(new Task("Ann", "Infrastructure", "Security", Priority.HIGH, Status.IN_PROGRESS));
		data.add(new Task("Ann", "Infrastructure", "Password Policy", Priority.MEDIUM, Status.IN_PROGRESS));
		data.add(new Task("Ann", "Research", "Cloud Solutions", Priority.MEDIUM, Status.IN_PROGRESS));
		data.add(new Task("Ann", "Data Design", "Encryption Policy", Priority.HIGH));
		data.add(new Task("Ann", "Data Design", "Project Table", Priority.MEDIUM));
		data.add(new Task("Ann", "Data Access", "Write Views", Priority.LOW, Status.IN_PROGRESS));
		
		data.add(new Task("Bob", "Infrastructure", "Security", Priority.HIGH, Status.IN_PROGRESS));
		data.add(new Task("Bob", "Infrastructure", "Password Policy", Priority.MEDIUM));
		data.add(new Task("Bob", "Data Design", "Encryption Policy",Priority.HIGH));
		data.add(new Task("Bob", "Data Access", "Write Views", Priority.LOW, Status.IN_PROGRESS));
		
		data.add(new Task("Carol", "Infrastructure", "Logging", Priority.HIGH, Status.IN_PROGRESS));
		data.add(new Task("Carol", "Infrastructure", "DB Access", Priority.MEDIUM));
		data.add(new Task("Carol", "Infrastructure", "Password Policy", Priority.MEDIUM));
		data.add(new Task("Carol", "Data Design", "Task Table", Priority.HIGH));
		data.add(new Task("Carol", "Data Access", "Write Views", Priority.LOW));	
		
		Scanner sc = new Scanner (System.in);
		String name = sc.next();
		data.getTasks(name).forEach(System.out::println);
		
		/*System.out.println("All Tasks :");
		data.getTasks("all").forEach(System.out::println);
		
		System.out.println("Ann Tasks :");
		data.getTasks("Ann").forEach(System.out::println);
		
		System.out.println("Bob Tasks :");
		data.getTasks("Bob").forEach(System.out::println);
		
		System.out.println("Carrol Tasks :");
		data.getTasks("Carrol").forEach(System.out::println);*/
		
	}
}
