package SetOperations;

import SetOperations.Task;

import java.util.Objects;

public class Task implements Comparable<Task> {
    public enum Status { IN_QUEUE, ASSIGNED, IN_PROGRESS }
    public enum Prior { HIGH, MEDIUM, LOW }

    private String project;
    private String description;
    private String assignee;
    private Prior prior;
    private Status status;

    public Task(String project, String description, String assignee, Prior prior, Status status) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.prior = prior;
        this.status = status;
    }

    public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Prior getPrior() {
		return prior;
	}

	public void setPrior(Prior prior) {
		this.prior = prior;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getProject() {
        return project;
    }

    public String getDescription() {
        return description;
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
    public int compareTo(Task o) {
        int result = this.project.compareTo(o.project);
        return (result != 0) ? result : this.description.compareTo(o.description);
    }

    @Override
    public String toString() {
        return project + " - " + description;
    }
}
