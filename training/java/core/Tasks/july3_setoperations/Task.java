package corejava.july3_setoperations;

import java.util.Objects;

enum Status{
	IN_QUEUE,
	ASSIGNED,
	IN_PROGRESS
}

enum Priority{
	HIGH,
	MED,
	LOW
}
public class Task implements Comparable<Task>{
	private String project;
	private String description;
	private String assignee;
	private Priority priority;
	private Status status;
	
	public Task(String project, String description, String assignee, Priority priority, Status status) {
		this.project = project;
		this.description = description;
		this.assignee = assignee;
		this.priority = priority;
		this.status = status;
	}
	
	public String getProject() {
		return project;
	}
	
	public String getDescription() {
		return description;
	}

	public String getAssignee() {
		return assignee;
	}

	public Priority getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}
	
	@Override
    public int compareTo(Task otherTask) {
        int projectComparison = this.project.compareTo(otherTask.project);
        if (projectComparison != 0) {
            return projectComparison;
        }
        return this.description.compareTo(otherTask.description);
    }
	
	 @Override
	    public String toString() {
	        return "Task{" +
	               "project='" + project + '\'' +
	               ", description='" + description + '\'' +
	               ", priority=" + priority +
	               ", status=" + status +
	               ", assignedTo='" + assignee + '\'' +
	               '}';
	    }
	 
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) 
	        	return true;
	        if (o == null || getClass() != o.getClass()) 
	        	return false;
	        Task task = (Task) o;
	        return Objects.equals(project, task.project) && Objects.equals(description, task.description);
	    }
	 
	 @Override
	 public int hashCode() {
		 return Objects.hash(project, description);
	 }
	
}
