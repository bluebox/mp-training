package SetOperations;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskData {
	private HashSet<Task> tasks;
	
	public TaskData(HashSet<Task> tasks) {
		this.tasks=tasks;
	}

	public HashSet<Task> getTasks() {
		return tasks;
	}

	public void setTasks(HashSet<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void printSortedTasks() {
		ArrayList<Task> tasks=new ArrayList<>(this.tasks);
		tasks.sort(Comparator.comparing(Task::getProject).thenComparing(Task::getDescription));
		tasks.forEach(t -> System.out.println(t));
	}
	
	public void printTasks() {
		this.tasks.forEach(t -> System.out.println(t));
	}
	
	public void printTasks(String assignee) {
		for(Task t:this.tasks) {
			if(t.getAssignee()==assignee) {
				System.out.println(t);
			}
		}
	}
}