package setOperationsChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 

public class TaskData{
	private Set<Task> tasks = new HashSet<>();
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}

	public <T> Set<Task> getTasks(String assignee) {
		if(assignee.equalsIgnoreCase("all")) {
			List<Task> allTasks =new ArrayList<>(this.tasks);
			allTasks.sort(Comparator.comparing(Task::getProject).thenComparing(Task::getDescription).thenComparing(Task::getAssignee));
			return new HashSet<>(allTasks);
		}
		Set<Task> filteredTasks = new HashSet<>();
		for(Task task: tasks) {
			if(task.getAssignee().equalsIgnoreCase(assignee)) {
				filteredTasks.add(task);
			}
		}
		return filteredTasks;
	}
	
//	public int compareTo(Task o) {
//		
//		int projectComparison = this.project.compareTo(o.project);
//		if(projectComparison != 0) {
//			return projectComparison;
//		}
//		
//		return this.description.compareTo(o.description);
//	}

}
