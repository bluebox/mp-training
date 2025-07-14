package model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

public class ProjectWeekKey implements Comparable<ProjectWeekKey> {
    private final String projectId;
    private final int year, week;

    public ProjectWeekKey(String projectId, LocalDate date) {
        this.projectId = projectId;
        WeekFields wf = WeekFields.of(Locale.getDefault());
        this.week = date.get(wf.weekOfWeekBasedYear());
        this.year = date.get(wf.weekBasedYear());
    }

    @Override
    public int compareTo(ProjectWeekKey o) {
        int c = projectId.compareTo(o.projectId);
        if (c != 0) return c;
        if (year != o.year) return year - o.year;
        return week - o.week;
    }

    @Override public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof ProjectWeekKey)) return false;
        ProjectWeekKey k = (ProjectWeekKey)o;
        return year==k.year && week==k.week && projectId.equals(k.projectId);
    }

    @Override public int hashCode() {
        return Objects.hash(projectId, year, week);
    }

    @Override public String toString() {
        return projectId + " | Week " + week + " of " + year;
    }
}
