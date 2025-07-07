package SetOperatorsChallenge;
 
import java.util.HashSet;
import java.util.Set;
 
public class TaskData{
	
	private Set<Task> ann = new HashSet<>();
	private Set<Task> bob = new HashSet<>();
	private Set<Task> carrol = new HashSet<>();
	private Set<Task> fullTask = new HashSet<>();
	
	public void addTask(Task task) {
		switch(task.getAssignee()) {
		case "all":
			fullTask.add(task);
			break;
		case "Ann":
			ann.add(task);
			break;
		case "Bob":
			bob.add(task);
			break;
		case "Carrol":
			carrol.add(task);
			break;
		}
	}

	public <T> Set<Task> getTasks(String assignee) {
		switch(assignee) {
			case "all":
				return fullTask;
			case "Ann":
				return ann;
			case "Bob":
				return bob;
			case "Carrol":
				return carrol;
			default:
				return null;
		}
	}
}
