package com.prana;


import java.util.Objects;

public class Task implements Comparable<Task> {
    public enum Status {
        NOT_ASSIGNED, ASSIGNED, IN_PROGRESS
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private String assignee;
    private String projectName;
    private String description;
    private Status status;
    private Priority priority;

    public Task(String assignee, String projectName, String description, Priority priority, Status status) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(projectName, task.projectName) &&
               Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, description);
    }

    @Override
    public int compareTo(Task other) {
        int projectComp = this.projectName.compareTo(other.projectName);
        return projectComp != 0 ? projectComp : this.description.compareTo(other.description);
    }

    @Override
    public String toString() {
    	return String.format("%s - %s (%s, %s, %s)", assignee, projectName, description, priority, status);

    }
}
