package SetOperationsExtends;
import java.util.Arrays;
import java.util.HashSet;

public class TaskMain {
	public static void main(String [] args) {
		Task t1=new Task("Vardhan", "Mart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		Task t2=new Task("Aasritha", "Rapido", "This is a transport application", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM);
		Task t3=new Task("Charan", "Uber", "This is a transport application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t4=new Task("Tarun", "Apollo", "This is a medical application", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t5=new Task("Adithya", "Flipkart", "This is an e-commerce application", Task.Status.ASSIGNED, Task.Priority.HIGH);
		HashSet<Task> taskSet=new HashSet<Task>(Arrays.asList(t1,t2,t3,t4,t5));
		TaskData taskData=new TaskData(taskSet);
		System.out.println("================= All Tasks ===============");
		taskData.printTasks();
		String name="Tarun";
		System.out.printf("\n================= All Tasks Of %s ===============\n",name);
		taskData.printTasks(name);
		System.out.println(t4);
		System.out.printf("\n================= All Sorted Tasks are ===============\n");
		taskData.printSortedTasks();
	}
	
	
}