package July3;

public class Task implements Comparable <Task> {
	
	private String assignee;
	private String projectName;
	private String description;
	private Status status;
	private Priority priority;
	

	public Task(String projectName, String description, Priority priority) {
		this(null, projectName, description, priority);
	}

	public Task(String assignee, String projectName, String description, Priority priority) {
		this.assignee = assignee;
		this.projectName = projectName;
		this.description = description;
		this.status = (assignee == null) ? Status.IN_QUEUE : Status.ASSIGNED;
		this.priority = priority;
	}

	public Task(String assignee, String projectName, String description,  Priority priority, Status status) {
		this.assignee = assignee;
		this.projectName = projectName;
		this.description = description;
		this.status = (status != null) ? status : ((assignee == null) ? Status.IN_QUEUE : Status.ASSIGNED);
		this.priority = priority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public int compareTo(Task o) {
		int nameCompare = this.projectName.compareTo(o.projectName);
		if(nameCompare != 0) return nameCompare;
		return this.description.compareTo(o.description);
	}

	@Override
	public String toString() {
		//return "Assignee = " + assignee + ", Project Name = " + projectName + ", Description = " + description	+ ", Status = " + status + ", Priority = " + priority;
		return ("%-5s %-15s %-17s %-12s %-10s").formatted(assignee, projectName, description, status, priority);
	}
	
}
 