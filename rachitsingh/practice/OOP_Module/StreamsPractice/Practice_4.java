// Function is a functional interface, it represents a function that takes 1 argument and produces a result.

import java.util.function.Function;

public class Practice_4 {
    public static void main(String[] args) {
        Function<Integer, Integer> twiceIt = x -> 2 * x;
        Function<Integer, Integer> tripleIt = x -> 3 * x;

        // .andThen() is a default method of Function interface
        // .apply() is the abstract method which is implemented using lambda expression
        System.out.println(twiceIt.andThen(tripleIt).apply(13));

        // .identity() is a static method of Function interface that always returns its input argument
        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(6666));
    }
}
