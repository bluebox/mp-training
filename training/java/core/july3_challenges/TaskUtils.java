package july_3Challenges;
import java.util.*;

public class TaskUtils {

   
    public static Set<String> getUnion(List<Set<String>> sets) {
        Set<String> result = new HashSet<>();
        for (Set<String> s : sets) {
            result.addAll(s);
        }
        return result;
    }

    public static Set<String> getIntersect(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static Set<String> getDifference(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }
}
