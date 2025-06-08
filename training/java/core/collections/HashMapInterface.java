package collections;
import java.util.HashMap;
import java.util.Map;
public class HashMapInterface {
	public static void main(String[] args) {
		Map<String, Integer> studentMarks = new HashMap<String, Integer>();
		studentMarks.put("Surya", 95);
        studentMarks.put("Karna", 88);
        studentMarks.put("Indra", 72);
        studentMarks.put("Arjuna", 98);
        System.out.println("Student Marks: " + studentMarks);
        System.out.println("Surya's Marks: " + studentMarks.get("Surya"));
        System.out.println("Indrajeet marks (not found): " + studentMarks.get("Indrajeet"));
        
        System.out.println("Contains key 'Indra'? " + studentMarks.containsKey("Indra"));
        System.out.println("Contains value 69? " + studentMarks.containsValue(69));

        studentMarks.remove("Indra");
        System.out.println("After removing Indra: " + studentMarks);

        System.out.println("Number of students: " + studentMarks.size());

        System.out.println("Iterating");
        System.out.println("Keys:");
        for (String name : studentMarks.keySet()) {
            System.out.println("  " + name);
        }
        System.out.println("Values:");
        for (Integer marks : studentMarks.values()) {
            System.out.println("  " + marks);
        }
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println(studentMarks.keySet()); 
        for (String key : studentMarks.keySet()) {
            System.out.println(key+" : " + studentMarks.get(key));
        }
	}
}
