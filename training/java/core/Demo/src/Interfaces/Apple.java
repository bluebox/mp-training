package Interfaces;

public class Apple implements Edible{
	
	@Override
    public void howToEat() {
        System.out.println("Crunch the apple.");
    }

    
    @Override
    public void describe() {
        System.out.println("A fresh apple, good for health!");
    }

}
