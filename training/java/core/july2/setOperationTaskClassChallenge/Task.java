package dev.tulasidhar.july2.setOperationTaskClassChallenge;

public class Task  {
    private String assignee;
    private String projectName;
    private String taskDescription;
    private String status;
    private String priority;
    
    public Task(String assignee, String projectName, String taskDescription, String status, String priority) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.priority = priority;
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
    
    public String getStatus() {
        return status;
    }
    
    public String getPriority() {
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
        this.status = status;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return projectName.equals(task.projectName) && 
               taskDescription.equals(task.taskDescription);
    }
    
    @Override
    public int hashCode() {
        return projectName.hashCode() + taskDescription.hashCode();
    }
}
