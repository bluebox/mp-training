import java.util.Random;

public class MemberIdGenerator {
    private static final Random rand = new Random();

    public static String generateId() {
        int num = 1000 + rand.nextInt(9000); // Generates 4-digit number
        return "FIT" + num;
    }
    
}
