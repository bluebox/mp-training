package setOperationsChallenge;

public class TasksMain {
	public static void main(String[] args) {
		
		TaskData taskData = new TaskData();
		
		taskData.addTask(new Task("all","Infrastucture","Logging",Priority.HIGH));
		taskData.addTask(new Task("all","Infrastucture","DB Accss",Priority.MEDIUM));
		taskData.addTask(new Task("all","Infrastucture","Security",Priority.HIGH));
		taskData.addTask(new Task("all","Infrastucture","Password Policy",Priority.MEDIUM));
		
		taskData.addTask(new Task("all","Data Design","Task Table",Priority.MEDIUM));
		taskData.addTask(new Task("all","Data Design","Employee Table",Priority.MEDIUM));
		taskData.addTask(new Task("all","Data Design","Cross Reference",Priority.HIGH));
		taskData.addTask(new Task("all","Data Design","Encryption Policy",Priority.HIGH));
		
		taskData.addTask(new Task("all","Data Access","Write Views",Priority.LOW));
		taskData.addTask(new Task("all","Data Access","Set Up Users",Priority.LOW));
		taskData.addTask(new Task("all","Data Access","Set Up Access",Priority.LOW));
		
		taskData.addTask(new Task("Ann","Infrastucture","Security",Status.IN_PROGRESS,Priority.HIGH));
		taskData.addTask(new Task("Ann","Infrastucture","Password Policy",Status.IN_PROGRESS,Priority.MEDIUM));
		taskData.addTask(new Task("Ann", "Research", "Cloud solutions",Status.IN_PROGRESS,Priority.MEDIUM));
		taskData.addTask(new Task("Ann","Data Design","Encryption Policy",Priority.HIGH));
		taskData.addTask(new Task("Ann","Data Design","Project Table",Priority.MEDIUM));
		taskData.addTask(new Task("Ann","Data Access","Set Up Access",Status.IN_PROGRESS,Priority.LOW));
		
		taskData.addTask(new Task("Bob","Infrastucture","Security",Status.IN_PROGRESS,Priority.HIGH));
		taskData.addTask(new Task("Bob","Infrastucture","Password Policy",Priority.MEDIUM));
		taskData.addTask(new Task("Bob","Data Design","Encryption Policy",Priority.HIGH));
		taskData.addTask(new Task("Bob","Data Access","Write Views",Status.IN_PROGRESS,Priority.LOW));
		
		taskData.addTask(new Task("Carrol","Infrastucture","Logging",Status.IN_PROGRESS,Priority.HIGH));
		taskData.addTask(new Task("Carrol","Infrastucture","DB Accss",Priority.MEDIUM));
		taskData.addTask(new Task("Carrol","Infrastucture","Password Policy",Priority.MEDIUM));
		taskData.addTask(new Task("Carrol","Data Design","Task Table",Priority.MEDIUM));
		taskData.addTask(new Task("Carrol","Data Access","Write Views",Priority.LOW));
		
		System.out.println("All Tasks :");
		taskData.getTasks("all").forEach(System.out::println);
		
		System.out.println("Ann Tasks :");
		taskData.getTasks("Ann").forEach(System.out::println);
		
		System.out.println("Bob Tasks :");
		taskData.getTasks("Bob").forEach(System.out::println);
		
		System.out.println("Carrol Tasks :");
		taskData.getTasks("Carrol").forEach(System.out::println);
	}
}
