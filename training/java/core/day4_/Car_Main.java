package com.day4_;

public class Car_Main {
    public static void main(String[] args) {
        Car[] cars = {
            new Petrol_Car("Petrol Car"),
            new Electric_Car("Electric Car"),
            new Hybrid_Car("Hybrid Car")
        };

        for (Car car : cars) {
            car.startEngine();
            car.drive();
            System.out.println("Runtime type: " + car.getClass().getSimpleName());
            System.out.println();
        }
    }
}