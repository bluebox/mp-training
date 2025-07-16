package corejava.july3_setoperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperations {

    public static <T> Set<T> getUnion(List<Set<T>> sets) {
        Set<T> unionSet = new HashSet<>();
        for (Set<T> set : sets) {
            unionSet.addAll(set);
        }
        return unionSet;
    }

    public static <T> Set<T> getIntersect(Set<T> set1, Set<T> set2) {
        Set<T> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }

    public static <T> Set<T> getDifference(Set<T> set1, Set<T> set2) {
        Set<T> differenceSet = new HashSet<>(set1);
        differenceSet.removeAll(set2);
        return differenceSet;
    }
}