import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodAndLambdaChallenge {
    public static void main(String[] args) {
        String[] names = {"John", "Jane", "Bob", "Anna", "Tom", "Emily"};

        List<Function<String, String>> transformations = new ArrayList<>();
        transformations.add(String::toUpperCase);
        transformations.add(name -> name + " " + (char)(Math.random() * 26 + 'A') + ".");
        transformations.add(name -> name + " " + new StringBuilder(name).reverse().toString());
        transformations.add(name -> name + " Smith");

        applyTransformations(names, transformations);

        System.out.println(Arrays.toString(names));

        List<String> modifiableNames = new ArrayList<>(Arrays.asList(names));
        modifiableNames.removeIf(name -> name.split(" ")[0].equals(name.split(" ")[name.split(" ").length - 1]));
        System.out.println(modifiableNames);
    }

    public static void applyTransformations(String[] names, List<Function<String, String>> transformations) {
        for (int i = 0; i < names.length; i++) {
            for (Function<String, String> transformation : transformations) {
                names[i] = transformation.apply(names[i]);
            }
        }
    }
}
