package setOperationsChallenge;

import java.util.Objects;


public class Task{
	
	private String assignee;
	
	private String project;
	
	private String description;
	
	private Status status;
	
	private Priority priority;
	
	public Task(String assignee, String project, String description, Status status, Priority priority) {
		this.assignee = assignee;
		this.project = project;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}
	
	public Task(String assignee, String project, String description,Priority priority) {
		this(assignee, project, description, Status.NOT_YET_ASSIGNED, priority);
	}

	public Task(String assignee, String project, String description,Status status) {
		this(assignee, project, description, status, Priority.LOW);
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
		return Objects.hash(description, project,assignee);
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
		return Objects.equals(description, other.description) && Objects.equals(project, other.project);
	}

	@Override
	public String toString() {
		return "Task [assignee=" + assignee + ", project=" + project + ", description=" + description + ", status="
				+ status + ", priority=" + priority + "]";
	}	
}