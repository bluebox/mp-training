package Day8_04_07_practice;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Consumer<String> printConsumer = s ->{ 
        	System.out.println("Printing s value: " + s);
        		};
        printConsumer.accept("Hello, Consumer!");

        Consumer<Integer> doubleAndPrintConsumer = num -> {
            int doubledNum = num * 2;
            System.out.println("Doubled value: " + doubledNum);
        };
        doubleAndPrintConsumer.accept(5);

        Consumer<String> uppercaseConsumer = s -> System.out.println("Uppercase: " + s.toUpperCase());
        printConsumer.andThen(uppercaseConsumer).accept("chaining consumers");
    }
}