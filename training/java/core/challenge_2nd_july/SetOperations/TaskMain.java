package challenge_2nd_july.SetOperations;

import java.util.Arrays;
import java.util.HashSet;

public class TaskMain {
	public static void main(String [] args) {
		Task t1=new Task("adheesh", "medplus-java", "This is an e-commerce application in java", Task.Status.ASSIGNED, Task.Priority.HIGH);
		Task t2=new Task("tarun", "medplus-python", "This is a task in python", Task.Status.IN_PROGRESS, Task.Priority.MEDIUM);
		Task t3=new Task("adarsh", "medplus-react", "This is a react task", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t4=new Task("ravi", "medplus-spring", "This is a task in spring", Task.Status.NOT_YET_ASSIGNED, Task.Priority.LOW);
		Task t5=new Task("ramu", "medplus-django", "This is a task in django", Task.Status.ASSIGNED, Task.Priority.HIGH);
		HashSet<Task> taskSet=new HashSet<Task>(Arrays.asList(t1,t2,t3,t4,t5));
		TaskData taskData=new TaskData(taskSet);
		System.out.println("================= All Tasks ===============");
		taskData.printTasks();
		String name="adheesh";
		System.out.printf("\n================= All Tasks Of %s ===============\n",name);
		taskData.printTasks(name);
		System.out.println(t4);
		System.out.printf("\n================= All Sorted Tasks are ===============\n");
		taskData.printSortedTasks();
	}
	
	
}
