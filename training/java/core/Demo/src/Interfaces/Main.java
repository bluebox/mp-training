package Interfaces;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Apple apple = new Apple();
        Chicken chicken = new Chicken();

        System.out.println("Apple");
        apple.howToEat(); 
        apple.describe(); 
        System.out.println("Type: " + Edible.EDIBLE_TYPE); 
        System.out.println("Chicken");
        chicken.howToEat(); 
        chicken.describe(); 
        System.out.println("Type: " + Edible.EDIBLE_TYPE);

        System.out.println("Polymorphic Usage");
        Edible item1 = new Apple();
        Edible item2 = new Chicken();

        item1.howToEat();
        item2.howToEat();

        
        System.out.println("Static Method");
        Edible.printDietaryAdvice();

	}

}
