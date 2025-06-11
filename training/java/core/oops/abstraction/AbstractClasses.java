package oops.abstraction;

abstract class Vehicle {
    abstract void move();

    void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
    @Override
    void move() {
        System.out.println("Car is moving");
    }
}

public class AbstractClasses {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
        v.move();
    }
}
