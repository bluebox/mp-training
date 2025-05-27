package methods;

import java.util.function.Function;

public class ParametersPassing {

    public void functionPassing(Function<Integer, Integer> func, Function<Integer, Integer> func2, int a) {
        System.out.println(func.apply(a));
        System.out.println(func2.apply(a));
    }

    public void functionPassing(Function<String, String> func, Function<String, String> func2, String a) {
        System.out.println(func.apply(a));
        System.out.println(func2.apply(a));
    }
    public static void main(String[] args) {
        ParametersPassing obj = new ParametersPassing();

            // Integer function example
            obj.functionPassing(
                x -> x + 10,
                x -> x * 2,
                5
            );

            // String function example
            obj.functionPassing(
                s -> s.toUpperCase(),
                s -> s + " World!",
                "hello"
            );
        }
}