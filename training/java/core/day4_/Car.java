
package com.day4_;
public class Car {
    private String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println(getClass().getSimpleName() + "Starting engine");
    }

    public void drive() {
        System.out.println(getClass().getSimpleName() + "Driving");
        runEngine();
    }

    protected void runEngine() {
        System.out.println(getClass().getSimpleName() + "Running engine");
    }
}