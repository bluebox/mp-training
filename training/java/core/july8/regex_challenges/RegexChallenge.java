package dev.tulasidhar.july8.regex_challenges;

public class RegexChallenge {
    public static void main(String[] args) {
        String pattern1 = "Hello, World!";
        String pattern2 = "[A-Z][a-z]*\\.";
        String pattern3 = "[A-Z][\\w\\s,':!?-]*[!?]";
        
        System.out.println("Challenge 1:");
        System.out.println("Hello, World!".matches(pattern1));
        System.out.println("Hello World".matches(pattern1));
        
        System.out.println("\nChallenge 2:");
        System.out.println("The bike is red.".matches(pattern2));
        System.out.println("I am a new student.".matches(pattern2));
        System.out.println("Hello World.".matches(pattern2));
        System.out.println("How are you?".matches(pattern2));
        
        System.out.println("\nChallenge 3:");
        System.out.println("The bike is red, and has flat tires.".matches(pattern3));
        System.out.println("I love being a new L.P.A. student!".matches(pattern3));
        System.out.println("Hello, friends and family: Welcome!".matches(pattern3));
        System.out.println("How are you, Mary?".matches(pattern3));
        System.out.println("Hello, World.".matches(pattern3));
    }
}