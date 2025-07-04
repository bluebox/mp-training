package Day7_03_07_setChallenge;

import java.util.Objects;

public class Task implements Comparable<Task> {
    enum Status {
        ASSIGNED, IN_PROGRESS, NOT_ASSIGNED, UNKNOWN
    }

    enum Priority {
        HIGH, MEDIUM, LOW, UNKNOWN
    }

    private String assignedTo;
    private String projectName;
    private String taskDetails;
    private Status status;
    private Priority priority;

    public Task(String assignedTo, String projectName, String taskDetails, Status status, Priority priority) {
        this.assignedTo = assignedTo;
        this.projectName = projectName;
        this.taskDetails = taskDetails;
        this.status = status;
        this.priority = priority;
    }

    public Task(String assignedTo, String projectName, String taskDetails) {
        this(assignedTo, projectName, taskDetails, Status.UNKNOWN, Priority.UNKNOWN);
    }

    public Task() {
        this("N/A", "N/A", "N/A", Status.UNKNOWN, Priority.UNKNOWN);
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task item = (Task) obj;
        return Objects.equals(projectName, item.projectName)
                && Objects.equals(taskDetails, item.taskDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, taskDetails);
    }

    @Override
    public String toString() {
        return "[ Assigned To: " + assignedTo +
                ", Project: " + projectName +
                ", Task: " + taskDetails +
                ", Status: " + status +
                ", Priority: " + priority + " ]";
    }

    @Override
    public int compareTo(Task other) {
        return this.projectName.compareTo(other.projectName);
    }
}
