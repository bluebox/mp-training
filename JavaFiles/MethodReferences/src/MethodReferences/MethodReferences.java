package MethodReferences;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Arrays;

public class MethodReferences {
    public static void printWords(String sentence) {
        String[] parts = sentence.split(" ");
        for (String part : parts) {
            System.out.println("Word: " + part);
        }
    }

    public void printUpperCase(String sentence) {
        String[] parts = sentence.split(" ");
        for (String part : parts) {
            System.out.println("Uppercase: " + part.toUpperCase());
        }
    }

    public static void main(String[] args) {
        String testSentence = "Hello world from Java";

        
        Consumer<String> staticRef = MethodReferences::printWords;
        System.out.println("1. Static Method Reference:");
        staticRef.accept(testSentence);

       
        MethodReferences lambdaInstance = new MethodReferences();
        Consumer<String> instanceRef = lambdaInstance::printUpperCase;
        System.out.println("\n2. Instance Method Reference:");
        instanceRef.accept(testSentence);

     
        Function<String, String> toUpper = String::toUpperCase;
        System.out.println("\n3. Arbitrary Object Method Reference:");
        System.out.println("Full sentence in uppercase: " + toUpper.apply(testSentence));

       
        Function<String, StringBuilder> stringBuilderRef = StringBuilder::new;
        System.out.println("\n4. Constructor Reference:");
        StringBuilder sb = stringBuilderRef.apply(testSentence);
        System.out.println("StringBuilder content: " + sb);

       
        System.out.println("\n5. Method Reference in Stream:");
        Arrays.stream(testSentence.split(" "))
              .forEach(System.out::println);
    }
}