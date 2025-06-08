package collections;
import java.util.Map;
import java.util.TreeMap;
public class TreeMapInterface {
	public static void main(String[] args) {
        Map<String, String> phoneBook = new TreeMap<>();

        phoneBook.put("Dharma", "998877");
        phoneBook.put("Bheema", "665544");
        phoneBook.put("Arjuna", "332211");
        phoneBook.put("Nakula", "123456");
        phoneBook.put("Sahadeva", "987654");

        System.out.println("Phone Book (sorted by name): " + phoneBook);
        
        System.out.println("First entry: " + ((TreeMap<String, String>) phoneBook).firstEntry());
        System.out.println("Last entry: " + ((TreeMap<String, String>) phoneBook).lastEntry());
        System.out.println("Entry for 'C' or later: " + ((TreeMap<String, String>) phoneBook).ceilingEntry("C"));
        System.out.println("Entry for 'C' or before: " + ((TreeMap<String, String>) phoneBook).floorEntry("C"));
    }
}