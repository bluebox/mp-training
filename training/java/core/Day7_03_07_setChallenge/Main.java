package Day7_03_07_setChallenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getAllTasks();

        System.out.println("All Tasks");
        sortAndPrint(tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);

        Set<Task> annsTasks = TaskData.getTasks("Ann");
        System.out.println("\nAnn's Tasks");
        sortAndPrint(annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");

        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnion(sets);
        System.out.println("Assigned Tasks");
        sortAndPrint(assignedTasks);

        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        System.out.println("The True All Tasks");
        sortAndPrint(everyTask);

        Set<Task> missingTasks = getDifference(everyTask, tasks);
        System.out.println("Missing Tasks");
        sortAndPrint(missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        System.out.println("Unassigned Tasks");
        sortAndPrint(unassignedTasks, sortByPriority);

        Set<Task> overlap = getUnion(List.of(getIntersect(annsTasks, bobsTasks),getIntersect(carolsTasks, bobsTasks),getIntersect(annsTasks, carolsTasks)));
        System.out.println("Assigned to Multiples");
        sortAndPrint(overlap, sortByPriority);

        List<Task> overlapping = new ArrayList<>();
        for (Set<Task> set : sets) {
            Set<Task> dupes = getIntersect(set, overlap);
            overlapping.addAll(dupes);
        }

        Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
        System.out.println("Overlapping");
        sortAndPrint(overlapping, priorityNatural);
    }

    private static void sortAndPrint(Collection<Task> collection) {
        sortAndPrint(collection, null);
    }

    private static void sortAndPrint(Collection<Task> collection, Comparator<Task> sorter) {
        List<Task> list = new ArrayList<>(collection);
        if (sorter != null) list.sort(sorter);
        else Collections.sort(list);
        list.forEach(System.out::println);
    }

    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> union = new HashSet<>();
        for (var taskSet : sets) {
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a, Set<Task> b) {
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDifference(Set<Task> a, Set<Task> b) {
        Set<Task> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }
}
