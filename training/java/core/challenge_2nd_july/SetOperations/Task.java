package challenge_2nd_july.SetOperations;

import java.util.Objects;

public class Task {
	enum Status{
		ASSIGNED, IN_PROGRESS, NOT_YET_ASSIGNED, UNKNOWN
	};
	
	enum Priority{
		HIGH, LOW, MEDIUM, UNKNOWN
	};
	
	private String assignee;
	private String project;
	private String description;
	private Status status;
	private Priority priority;
	public Task(String assignee, String project, String description, Status status, Priority priority) {
		super();
		this.assignee = assignee;
		this.project = project;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}
	
	
	public Task() {
		this("not there","not there","not there",Task.Status.UNKNOWN,Task.Priority.UNKNOWN);
	}
	
	public Task(String assignee, String project, String description) {
		this(assignee, project, description, Task.Status.UNKNOWN, Task.Priority.UNKNOWN);
	}
	
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		return Objects.hash(assignee, description, priority, project, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(assignee, other.assignee) && Objects.equals(description, other.description)
				&& priority == other.priority && Objects.equals(project, other.project) && status == other.status;
	}
	
}
