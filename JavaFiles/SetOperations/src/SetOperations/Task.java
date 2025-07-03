package SetOperations;

public class Task implements Comparable<Task>{
	private String assignee;
	private String project;
	private String description;
	private String status;
	private Priority priority;
	
	
	public Task(String assignee, String project, String description, String status, Priority priority) {
		this.assignee = assignee;
		this.project = project;
		this.description = description;
		this.status = status;
		this.priority = priority;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		return "Task [assignee=" + assignee + ", project=" + project + ", description=" + description + ", status="
				+ status + ", priority=" + priority + "]";
	}

	@Override
	public int compareTo(Task o) {
		int projectCompare = this.project.compareTo(o.project);
        if (projectCompare != 0)
        	return projectCompare;
        return this.description.compareTo(o.description);
	}

}
