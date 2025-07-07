package dev.tulasidhar.july3.setOperationsExtended;

enum Status { IN_QUEUE, ASSIGNED, IN_PROGESS};

enum Priority{
	HIGH,MEDIUM,LOW
}


public class Task implements Comparable<Task>{
    private String assignee;
    private String projectName;
    private String taskDescription;
    private Status status;
    private Priority priority;

    
    public Task(String projectName, String taskDescription, String status, String priority) {
		this("",projectName,taskDescription,status,priority);
	}

	public Task(String assignee, String projectName, String taskDescription, String status, String priority) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        
        this.priority = Priority.valueOf(priority);
    }
    
    public String getAssignee() {
        return assignee;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public String getTaskDescription() {
        return taskDescription;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public void setStatus(String status) {
    	
        this.status = Status.valueOf(status.toUpperCase());
    }
    
    public void setPriority(String priority) {
    	
    	this.priority = Priority.valueOf(priority.toUpperCase());
    }
    
    
    
    @Override
    public String toString() {
        return "Task{" +
                "assignee='" + assignee + '\'' +
                ", projectName='" + projectName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

	@Override
	public int compareTo(Task o) {
		if(o.projectName.compareTo(this.projectName) != 0 ) {
			return this.projectName.compareTo(o.projectName);
		}
		return o.taskDescription.compareTo(this.taskDescription);
	}
    
    
}
