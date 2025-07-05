import java.util.*;

public class SetOperations {
    public static <T> Set<T> getUnion(List<Set<T>> sets) {
        Set<T> result = new HashSet<>();
        for (Set<T> s : sets) {
            result.addAll(s);
        }
        return result;
    }
    public static <T> Set<T> getIntersect(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static <T> Set<T> getDifference(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1,2,3,4));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(3,4,5));
        Set<Integer> s3 = new HashSet<>(Arrays.asList(5,6));

        System.out.println("Union: " + getUnion(Arrays.asList(s1,s2,s3)));
        System.out.println("Intersect(s1,s2): " + getIntersect(s1,s2));
        System.out.println("Difference(s1,s2): " + getDifference(s1,s2));
    }
}