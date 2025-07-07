package day7;

import java.util.Objects;

public class Task implements Comparable<Task>{
	private String project;
	private String description;
	private String assignee;
	private Priority priority;
	private Status status;

	public Task(String project, String description, String assignee, Priority priority, Status status) {
		super();
		this.project = project;
		this.description = description;
		this.assignee = assignee;
		this.priority = priority;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Task task = (Task) obj;
		return this.project.equals(task.project) && this.description.equals(task.description);
	}

	@Override
	public String toString() {
		return String.format("Project: %s, description: %s,"
				+ " assignee: %s, priority: %s, status: %s",
				project,description,assignee,priority,status);
	}

	@Override
	public int compareTo(Task otherTask) {
		int answer = this.project.compareTo(otherTask.project);
		if (answer != 0) {
			return answer;
		}
		return this.description.compareTo(otherTask.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(project, description);
	}

}

enum Priority {
	LOW, MEDIUM, HIGH
}

enum Status {
	NOT_YET_ASSIGNED, ASSIGNED, IN_PROGRESS
}