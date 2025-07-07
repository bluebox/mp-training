package SetOperatorsChallenge;

import java.util.HashSet;
import java.util.Set;

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
		
		System.out.println("Ann Tasks :");
		var annTasks=taskData.getTasks("Ann");
		annTasks.forEach(System.out::println);
		
		System.out.println("Bob Tasks :");
		var bobTasks=taskData.getTasks("Bob");
		bobTasks.forEach(System.out::println);
		
		System.out.println("Carrol Tasks :");
		var carrol=taskData.getTasks("Carrol");
		carrol.forEach(System.out::println);
		
		//Full Task list
		System.out.println("_".repeat(100));
		System.out.println("All Tasks :");
		var fullTasks =taskData.getTasks("all");
		fullTasks.forEach(System.out::println);

		//Tasks assigned to at least one member
		System.out.println("_".repeat(100));
		System.out.println("Tasks assigned to at least one member");
		var assignedTasks = getUnion(annTasks,bobTasks,carrol);
		assignedTasks.forEach(System.out::println);
		
		//tasks still needing assignment 
		System.out.println("_".repeat(100));
		System.out.println("Tasks need to assigned");
		Set<Task> unAssigned = getDifference(fullTasks,assignedTasks);
		unAssigned.forEach(System.out::println);
		
		//Tasks Assigned to multiple employees
		System.out.println("_".repeat(100));
		System.out.println("Tasks assigned to multiple employees");
		Set<Task> multiple = new HashSet<>(getIntersect(annTasks,bobTasks));
		multiple.addAll(getIntersect(annTasks,carrol));
		multiple.addAll(getIntersect(bobTasks,carrol));
		multiple.forEach(System.out::println);
	}
	
	
	public static  Set<Task> getUnion(Set<Task> task1, Set<Task> task2, Set<Task> task3){
		Set<Task> result = new HashSet<>();
		result.addAll(task1);
		result.addAll(task2);
		result.addAll(task3);
		return result;
	}
	
	public static Set<Task> getIntersect(Set<Task> task1, Set<Task> task2){
		Set<Task> result = new HashSet<>(task1);
		result.retainAll(task2);
		return result;
	}
	
	public static Set<Task> getDifference(Set<Task> task1, Set<Task> task2){
		Set<Task> result = new HashSet<>(task1);
		result.removeAll(task2);
		return result;
	}
}
