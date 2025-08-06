package com.example.springcorePractice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Car {

      @Autowired
      @Qualifier("e1")
    private Engine fieldInjectedEngine;

    private Engine constructorInjectedEngine;

    private Engine setterInjectedEngine;

    @Autowired
    public Car(Engine ConstructorInjectedEngine) {
        this.constructorInjectedEngine = ConstructorInjectedEngine;
    }

    @Autowired
    public void setSetterInjectedEngine(Engine setterInjectedEngine) {
        this.setterInjectedEngine = setterInjectedEngine;
    }

    public void startCar() {
        System.out.println("Starting car with:");
        System.out.print("Field Engine: ");
        fieldInjectedEngine.start();

        System.out.print("Constructor Engine: ");
        constructorInjectedEngine.start();

        System.out.print("Setter Engine: ");
        setterInjectedEngine.start();
    }
}
