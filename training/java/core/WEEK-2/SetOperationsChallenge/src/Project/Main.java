package Project;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	public static void main(String [] args) {
		Task t1=new Task("Rohit", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		Task t2=new Task("Varma", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM);
		Task t3=new Task("Visaal", "Uber", "This is a transport application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t4=new Task("Rohit", "Apollo", "This is a medical application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t5=new Task("Rohit", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		HashSet<Task> taskSet=new HashSet<Task>(Arrays.asList(t1,t2,t3,t4,t5));
		TaskData taskData=new TaskData(taskSet);
		System.out.println("================= All Tasks ===============");
		taskData.printTasks();
		String name="Rohit";
		System.out.printf("\n================= All Tasks Of %s ===============\n",name);
		taskData.printTasks(name);
		System.out.printf("\n================= All Sorted Tasks are ===============\n");
		taskData.printSortedTasks();
	}
	
	
}
