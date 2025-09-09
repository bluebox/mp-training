package com.day4_;

public class Petrol_Car extends Car {
    public Petrol_Car(String desc) {
        super(desc);
    }

    @Override
    public void startEngine() {
        System.out.println("Petrol Car --> Engine started with petrol");
    }

    @Override
    protected void runEngine() {
        System.out.println("Petrol Car --> Running with fuel");
    }
}
