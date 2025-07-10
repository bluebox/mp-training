package July2;

public class Task implements Comparable <Task> {
	
	private String assignee;
	private String projectName;
	private String description;
	private String status;
	private String priority;
	
	public Task(String assignee, String projectName, String description, String status, String priority) {
		this.assignee = assignee;
		this.projectName = projectName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
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
		return "Task [assignee=" + assignee + ", projectName=" + projectName + ", description=" + description
				+ ", status=" + status + ", priority=" + priority + "]";
	}
	
}
