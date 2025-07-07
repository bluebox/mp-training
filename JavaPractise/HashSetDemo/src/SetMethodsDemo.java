import java.util.*;

public class SetMethodsDemo {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("Apple");
        set1.add("Banana");
        set1.add("Cherry");
        set1.add("Date");
        set1.add("Apple"); 
        System.out.println("Set1 after add " + set1);
        set2.add("Elderberry");
        set2.add("Fig");
        set2.add("Grape");
        set2.addAll(set1); 
        System.out.println("Set2 after addAll(set1): " + set2);
        System.out.println("Set2 contains 'Banana': " + set2.contains("Banana"));
        System.out.println("Set2 contains all of set1: " + set2.containsAll(set1));
        set2.remove("Fig");
        System.out.println("Set2 after removing 'Fig': " + set2);
        set2.removeAll(set1);  
        System.out.println("Set2 after removeAll(set1): " + set2);
        set2.add("Banana");
        set2.add("Kiwi");
        set2.retainAll(set1);  
        System.out.println("Set2 after retainAll(set1): " + set2);
        System.out.println("Set1 size: " + set1.size());
        System.out.println("Is Set2 empty? " + set2.isEmpty());
        set2.clear();
        System.out.println("Set2 after clear(): " + set2);
        System.out.println("Iterating set1 using for-each:");
        for (String item : set1) {
            System.out.println(item);
        }
        System.out.println("Iterating set1 using Iterator:");
        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Iterating set1 using forEach():");
        set1.forEach(item -> System.out.println(item));
    }
}
