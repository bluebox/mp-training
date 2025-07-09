package day7;

import java.util.Set;
import java.util.TreeSet;

public class TaskData {
	private Set<Task> allTasks;
	private Set<Task> annsTasks;
	private Set<Task> bobsTasks;
	private Set<Task> carolsTasks;

	public TaskData(String allString, String annString, String bobString, String carolsString) {

		allTasks = new TreeSet<>();
		loadTasks(allTasks, allString, null);
		annsTasks = new TreeSet<>();
		loadTasks(annsTasks, annString, "Ann");
		bobsTasks = new TreeSet<>();
		loadTasks(bobsTasks, bobString, "Bob");
		carolsTasks = new TreeSet<>();
		loadTasks(carolsTasks, carolsString, "Carol");

	}

	private void loadTasks(Set<Task> taskSet, String tasksString, String assignee) {
		String[] tasksStrings = tasksString.split("\n");
		for (String taskString : tasksStrings) {
			String parts[] = taskString.split(",");
			String project = parts[0].trim();
			String description = parts[1].trim();
			Priority priority = Priority.valueOf(parts[2].trim().toUpperCase());
			Status status = Status.NOT_YET_ASSIGNED;
			if (assignee != null)
				status = parts.length > 3 ? Status.IN_PROGRESS : Status.ASSIGNED;
			Task task = new Task(project, description, assignee, priority, status);
			taskSet.add(task);
		}
	}

	public Set<Task> getTasks(String owner) {

		switch (owner.toLowerCase()) {
		case "ann":
			return this.annsTasks;
		case "bob":
			return this.bobsTasks;
		case "carol":
			return this.carolsTasks;
		case "all":
			return this.allTasks;
		default:
			return null;
		}
	}
}
