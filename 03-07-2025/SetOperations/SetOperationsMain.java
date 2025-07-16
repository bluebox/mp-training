package SetOperations;
import java.util.*;
public class SetOperationsMain {

	public static void main(String[] args) {
		
		
		
		Set<Task> annTasks = TaskData.getTasks("Ann");
        Set<Task> bobTasks = TaskData.getTasks("Bob");
        Set<Task> carolTasks = TaskData.getTasks("Carol");

        List<Set<Task>> allSets = List.of(annTasks, bobTasks, carolTasks);

        System.out.println("Union:");
        getUnion(allSets).forEach(System.out::println);

        System.out.println("\nIntersection (Ann & Bob):");
        getIntersect(annTasks, bobTasks).forEach(System.out::println);

        System.out.println("\nDifference (Ann - Carol):");
        getDifference(annTasks, carolTasks).forEach(System.out::println);
	}
	public static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> result = new HashSet<>();
        for (Set<Task> s : sets) {
            result.addAll(s);
        }
        return result;
    }

    public static Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}
