package SetOperations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnionIntersectionDifferenceInSets {
	
    public static <T> Set<T> getUnion(List<Set<T>> sets) {
        Set<T> union = new HashSet<>();
        for (Set<T> set : sets) {
            union.addAll(set);
        }
        return union;
    }
    
    public static <T> Set<T> getIntersection(List<Set<T>> sets) {
        Set<T> union = new HashSet<>();
        for (Set<T> set : sets) {
            union.retainAll(set);
        }
        return union;
    }
    
    public static <T> Set<T> getDifference(List<Set<T>> sets) {
        Set<T> union = new HashSet<>();
        for (Set<T> set : sets) {
            union.removeAll(set);
        }
        return union;
    }


	public static void main(String[] args) {
		Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> setB = new HashSet<>(Arrays.asList(3, 2, 5, 6));
        Set<Integer> setC = new HashSet<>(Arrays.asList(5, 6, 7, 8));
        
		Set<String> setD = new HashSet<>(Arrays.asList("A","B","C","D"));
		Set<String> setE = new HashSet<>(Arrays.asList("A","B","E","F"));

		List<Set<Integer>> sets = Arrays.asList(setA, setB, setC);
		
		List<Set<String>> sets1 = Arrays.asList(setD, setE);

		
        
        System.out.println(sets);
        System.out.println(sets1);        
        System.out.println("Union :"+ getUnion(sets));
        System.out.println("Union :"+ getUnion(sets1));
        
        System.out.println("Intersection :"+ getIntersection(sets));
        System.out.println("Intersection :"+ getIntersection(sets1));
        
        System.out.println("Difference :"+ getDifference(sets));
        System.out.println("Difference :"+ getDifference(sets1));


	}

}
