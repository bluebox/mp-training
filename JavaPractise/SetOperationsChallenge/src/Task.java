public class Task implements Comparable<Task> {
    public enum Status{
        NOT_ASSIGNED, ASSIGNED, IN_PROGRESS
    }
    public enum Priority{
        HIGH, MEDIUM, LOW
    }
    private String assignee;
    private String project;
    private String description;
    private Status status;
    private Priority priority;
    public Task(String assignee, String project, String description, Status status, Priority priority){
        this.assignee = assignee;
        this.project = project; 
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
    public String getAssignee(){
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

    public int compareTo(Task other) {
        int result = this.project.compareTo(other.project);
        if (result == 0) {
            result = this.description.compareTo(other.description);
        }
        return result;
    }

    public String toString() {
        return String.format("[%s] Project: %s, Task: %s, Assignee: %s, Priority: %s",
                status, project, description, assignee, priority);
    }
}

