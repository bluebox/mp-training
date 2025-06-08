package collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapInterface {
    public static void main(String[] args) {
        Map<String, String> user = new LinkedHashMap<>();

        user.put("user1", "10:00 AM");
        user.put("user3", "10:05 AM");
        user.put("user2", "10:02 AM");
        user.put("user1", "10:15 AM");

        System.out.println("User Login Order (insertion order): " + user);
    }
}