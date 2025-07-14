package SetOperators;

import java.util.Objects;

public class Task {
    private String project;
    private String description;

    public Task(String project, String description) {
        this.project = project;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(project, task.project) &&
               Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public String toString() {
        return "[" + project + ": " + description + "]";
    }
}
