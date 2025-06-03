public class Abstraction {
    public static void main(String[] args) {
        Car ferrari = new Ferrari();
        ferrari.drive();
        ferrari.honk();
    }
}

abstract class Car {
    public abstract void drive();
    public abstract void honk();
}

class Ferrari extends Car {
    public void drive() {
        System.out.println("I am driving!");
    }

    public void honk() {
        System.out.println("I am honking! pipeep!");
    }
}
