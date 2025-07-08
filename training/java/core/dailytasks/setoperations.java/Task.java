import java.util.Objects;

public class Task implements Comparable<Task> {
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

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(projectName, task.projectName) &&
               Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, description);
    }

    @Override
    public String toString() {
        return String.format("Task{Project='%s', Description='%s', Assignee='%s', Status='%s', Priority='%s'}",
                             projectName, description, assignee, status, priority);
    }

    @Override
    public int compareTo(Task other) {
        int projectComparison = this.projectName.compareTo(other.projectName);
        if (projectComparison != 0) {
            return projectComparison;
        }
        return this.description.compareTo(other.description);
    }
}