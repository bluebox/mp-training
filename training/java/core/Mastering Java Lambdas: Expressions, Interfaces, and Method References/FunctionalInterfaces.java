import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Consumer example
        Consumer<String> printer = (s) -> System.out.println(s);
        printer.accept("Hello, Consumer!");

        // Supplier example
        Supplier<String> nameSupplier = () -> "John Doe";
        String name = nameSupplier.get();
        System.out.println("Name: " + name);

        // Function example
        Function<Integer, String> intToString = (i) -> String.valueOf(i);
        String numberAsString = intToString.apply(42);
        System.out.println("Number as String: " + numberAsString);

        // Predicate example
        Predicate<String> nonEmptyString = (s) -> !s.isEmpty();
        boolean isNonEmpty = nonEmptyString.test("Hello");
        System.out.println("Is the string non-empty? " + isNonEmpty);
    }
}
