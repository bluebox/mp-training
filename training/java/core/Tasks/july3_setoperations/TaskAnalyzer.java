package corejava.july3_setoperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskAnalyzer {

    public static Set<Task> getFullTaskList(Set<Task> masterTaskList, Set<Task> annTasks, Set<Task> bobTasks, Set<Task> carolTasks) {
        return SetOperations.getUnion(List.of(masterTaskList, annTasks, bobTasks, carolTasks));
    }

    public static Set<Task> getTasksAssignedToAtLeastOneTeamMember(Set<Task> annTasks, Set<Task> bobTasks, Set<Task> carolTasks) {
        return SetOperations.getUnion(List.of(annTasks, bobTasks, carolTasks));
    }

    public static Set<Task> getTasksStillNeedingAssignment(Set<Task> masterTaskList, Set<Task> annTasks, Set<Task> bobTasks, Set<Task> carolTasks) {
        Set<Task> assignedToAtLeastOne = getTasksAssignedToAtLeastOneTeamMember(annTasks, bobTasks, carolTasks);
        return SetOperations.getDifference(masterTaskList, assignedToAtLeastOne);
    }

    public static Set<Task> getTasksAssignedToMultipleEmployees(Set<Task> annTasks, Set<Task> bobTasks, Set<Task> carolTasks) {
        Set<Task> assignedToMultiple = new HashSet<>();

        assignedToMultiple.addAll(SetOperations.getIntersect(annTasks, bobTasks));
        assignedToMultiple.addAll(SetOperations.getIntersect(annTasks, carolTasks));
        assignedToMultiple.addAll(SetOperations.getIntersect(bobTasks, carolTasks));

        return assignedToMultiple;
    }
}