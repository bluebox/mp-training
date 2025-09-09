package com.day4_;

public class Electric_Car extends Car {
    public Electric_Car(String desc) {
        super(desc);
    }

    @Override
    public void startEngine() {
        System.out.println("Electric Car --> Powering electric motor");
    }

    @Override
    protected void runEngine() {
        System.out.println("Electric Car --> Running on battery");
    }
}