package SetOperators;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Task> allTasks = TaskData.getTasks("all");
        Set<Task> annTasks = TaskData.getTasks("Ann");
        Set<Task> bobTasks = TaskData.getTasks("Bob");
        Set<Task> carolTasks = TaskData.getTasks("Carol");

        List<Set<Task>> employeeTasks = List.of(annTasks, bobTasks, carolTasks);

        Set<Task> fullTaskList = getUnion(List.of(allTasks, getUnion(employeeTasks)));
        Set<Task> assignedTasks = getUnion(employeeTasks);
        Set<Task> unassignedTasks = getDifference(allTasks, assignedTasks);

        Set<Task> annBobIntersect = getIntersect(annTasks, bobTasks);
        Set<Task> annCarolIntersect = getIntersect(annTasks, carolTasks);
        Set<Task> bobCarolIntersect = getIntersect(bobTasks, carolTasks);

        Set<Task> multiAssigned = getUnion(List.of(annBobIntersect, annCarolIntersect, bobCarolIntersect));

        System.out.println("Full Task List: " + fullTaskList);
        System.out.println("Tasks assigned to at least one team member: " + assignedTasks);
        System.out.println("Tasks still needing assignment: " + unassignedTasks);
        System.out.println("Tasks assigned to multiple employees: " + multiAssigned);
    }

    public static <T> Set<T> getUnion(List<Set<T>> sets) {
        Set<T> result = new HashSet<>();
        for (Set<T> set : sets) {
            result.addAll(set);
        }
        return result;
    }

    public static <T> Set<T> getIntersect(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static <T> Set<T> getDifference(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}
