package com.day4_;

public class Hybrid_Car extends Car {
    public Hybrid_Car(String desc) {
        super(desc);
    }

    @Override
    public void startEngine() {
        System.out.println("Hybrid Car --> Starting hybrid system");
    }

    @Override
    protected void runEngine() {
        System.out.println("Hybrid Car --> Running hybrid mode");
    }
}
