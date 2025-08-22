package com.prana;

import java.util.Objects;

public class Task implements Comparable<Task> {
    private final String project;
    private final String description;
    private final String assignee;
    private final Priority priority;
    private final Status status;

    public Task(String project, String description, String assignee, Priority priority, Status status) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public String getProject() { return project; }
    public String getDescription() { return description; }
    public String getAssignee() { return assignee; }
    public Priority getPriority() { return priority; }
    public Status getStatus() { return status; }

    @Override
    public int compareTo(Task other) {
        int projectCompare = this.project.compareTo(other.project);
        return (projectCompare != 0) ? projectCompare : this.description.compareTo(other.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(project, task.project) &&
               Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public String toString() {
        return "%s: %s (%s - %s - %s)".formatted(project, description, assignee, priority, status);
    }
}
