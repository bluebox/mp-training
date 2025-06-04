import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExpression {
    public static void main(String[] args) {
        // Create an array of String with first names in mixed case
String[] names = {"John", "Jane", "Bob", "Anna", "Tom", "Emily"};

// Transform names to all uppercase
Arrays.setAll(names, i -> names[i].toUpperCase());
System.out.println(Arrays.toString(names));

// Add a randomly generated middle initial and include a period
Arrays.setAll(names, i -> names[i] + " " + (char)(Math.random() * 26 + 'A') + ".");
System.out.println(Arrays.toString(names));

// Add a last name that is the reverse of the first name
Arrays.setAll(names, i -> names[i] + " " + new StringBuilder(names[i]).reverse().toString());
System.out.println(Arrays.toString(names));

// Create a new modifiable ArrayList from the names array, removing any names where the last name equals the first name
List<String> modifiableNames = new ArrayList<>(Arrays.asList(names));
modifiableNames.removeIf(name -> name.split(" ")[0].equals(name.split(" ")[1]));
System.out.println(modifiableNames);

    }
}
