package Project;
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
	
	public ArrayList<Task> getSortedTasks() {
		ArrayList<Task> tasks=new ArrayList<>(this.tasks);
		tasks.sort(Comparator.comparing(Task::getProject).thenComparing(Task::getDescription));
		tasks.forEach(t -> System.out.println(t));
		return tasks;
	}
	
	public HashSet<Task> getTasksOfAssignee(String assignee) {
		HashSet<Task> currSet=new HashSet<>();
		for(Task t:this.tasks) {
			if(t.getAssignee()==assignee) {
				currSet.add(t);
			}
		}
		return currSet;
	}
	
	public HashSet<Task> getStillNeedToBeAssignedTasks() {
		HashSet<Task> currSet=new HashSet<>();
		for(Task t:this.tasks) {
			if(t.getStatus()==Task.Status.NOT_YET_ASSIGNED) {
				currSet.add(t);
			}
		}
		return currSet;
	}
}
