package OOPS;

class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
    
    void run() {
        System.out.println("This animal is running.");

    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks.");
    }
}

public class Inheritance {
	
    public static void main(String args[]) {
        Dog myDog = new Dog();

        
        myDog.eat();
        myDog.bark();
        myDog.run();
    }
}


