package Collections;
import java.util.*;

public class Iterators {
	public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");
        words.add("Java");

        ListIterator<String> listIterator = words.listIterator();
        while (listIterator.hasNext()) {
            String word = listIterator.next();
            if (word.equals("World")) {
                listIterator.add("Awesome"); 
            } else if (word.equals("Java")) {
                listIterator.set("Programming"); 
            }
        }
        System.out.println("List after forward modifications: " + words);
        
        listIterator = words.listIterator();

        
        while(listIterator.hasPrevious()){
            listIterator.previous(); 
        }

        while (listIterator.hasNext()) {
            String word = listIterator.next();
            if (word.equals("World")) {
                listIterator.remove(); 
            }
        }
        System.out.println("List after removing 'World': " + words);
        
    }

}

