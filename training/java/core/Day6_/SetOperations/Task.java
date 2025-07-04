package SetOperations;

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
		this.assignee = assignee;
		this.project = project;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}
	
	public Task(String assignee, String project, String description) {
		this(assignee, project, description, Task.Status.UNKNOWN, Task.Priority.UNKNOWN);
	}

	public Task() {
		this("not given", "not given", "not given", Task.Status.UNKNOWN, Task.Priority.UNKNOWN);
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
	public boolean equals(Object task) {
		if(this==task) {
			return true;
		}
		if(task==null) {
			return false;
		}
		Task t=(Task) task;
		if(project.equals(t.getProject()) && description.equals(t.getProject())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(project,description);
	}
	
	@Override
	public String toString() {
		return "[ Assignee: "+this.getAssignee()+", Project: "+this.getProject()+", Description: "+this.getDescription()+", Status: "+this.getStatus().name()+", Priority: "+this.getPriority().name()+" ]";
	}
	
}