package Interfaces;

public interface Edible {
	
	String EDIBLE_TYPE = "Food";

 
    void howToEat();

    
    default void describe() {
        System.out.println("This item is " + EDIBLE_TYPE + " and can be eaten.");
    }

 
    static void printDietaryAdvice() {
        System.out.println("Remember to eat a balanced diet!");
    }

}
