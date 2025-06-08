// Predicate is a functional interface that represents a boolean-valued function with 1 argument.
// It is used to define a condition or filter that can be applied to a collection or when performing conditional checks.

import java.util.function.Predicate;

public class Practice_3 {
    public static void main(String[] args) {

        // storing the condition into variable.
        Predicate<Integer> isItEven = (x) -> x % 2 == 0;
        System.out.println(isItEven.test(5));

        Predicate<String> isWordStartingWithA = (String str) -> str.startsWith("A");
        System.out.println(isWordStartingWithA.test("Amarillo"));

        Predicate<String> isWordEndingWitht = (String str) -> str.endsWith("t");
        System.out.println(isWordEndingWitht.test("Albert"));

        Predicate<String> logicalAND = isWordStartingWithA.and(isWordEndingWitht);
        System.out.println(logicalAND.test("Albert"));

    }
}
