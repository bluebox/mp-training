package Project;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;

public class Main {
	
	public static void printGivenTasks(HashSet<Task> s) {
		for(Task t:s) {
			System.out.println(t);
		}
	}
	
	public static HashSet<Task> getUnion(HashSet<Task> s1, HashSet<Task> s2){
		s1.addAll(s2);
		return s1;
	}
	
	public static HashSet<Task> getIntersect(HashSet<Task> s1, HashSet<Task> s2){
		s1.retainAll(s2);
		return s1;
	}
	
	public static HashSet<Task> getDifference(HashSet<Task> s1, HashSet<Task> s2){
		s1.removeAll(s2);
		return s1;
	}
	
	public static void printGivenTasks(ArrayList<Task> s) {
		for(Task t:s) {
			System.out.println(t);
		}
	}
	
	public static void main(String [] args) {
		Task t1=new Task("Rohit", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		Task t2=new Task("Varma", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM);
		Task t3=new Task("Visaal", "Uber", "This is a transport application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t4=new Task("Varma", "Apollo", "This is a medical application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t5=new Task("Rohit", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		Task t6=new Task("Visaal", "Uber", "This is a transport application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		HashSet<Task> taskSet=new HashSet<Task>();
		taskSet.addAll(Arrays.asList(t1,t2,t3,t4,t5,t6));
		TaskData taskData=new TaskData(taskSet);
		System.out.println("================= All Tasks ===============");
		printGivenTasks(taskData.getTasks());
		String name1="Rohit";
		String name2="Varma";
		String name3="Visaal";
		System.out.printf("\n================= All Tasks Of %s ===============\n",name1);
		printGivenTasks(taskData.getTasksOfAssignee(name1));
		System.out.printf("\n================= All Tasks Of %s ===============\n",name2);
		printGivenTasks(taskData.getTasksOfAssignee(name2));
		System.out.printf("\n================= All Tasks Of %s ===============\n",name3);
		printGivenTasks(taskData.getTasksOfAssignee(name3));
		System.out.printf("\n================= All Sorted Tasks are ===============\n");
		printGivenTasks(taskData.getSortedTasks());
		System.out.printf("\n================= Tasks Still need to assigned are ===============\n");
		printGivenTasks(taskData.getStillNeedToBeAssignedTasks());
		System.out.printf("\n================= Union of Rohit tasks and varma tasks ===============\n");
		printGivenTasks(getUnion(taskData.getTasksOfAssignee(name1), taskData.getTasksOfAssignee(name2)));
		System.out.printf("\n================= Intersection of sill needs to be assigned and Visaals tasks ===============\n");
		printGivenTasks(getIntersect(taskData.getStillNeedToBeAssignedTasks(), taskData.getTasksOfAssignee(name3)));
		System.out.printf("\n================= Difference of sill needs to be assigned and Visaals tasks ===============\n");
		printGivenTasks(getDifference(taskData.getStillNeedToBeAssignedTasks(), taskData.getTasksOfAssignee(name3)));
	}
	
	
}
