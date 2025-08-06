package com.example.beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Person {
    private String name="Lucy";
//    @Autowired
    public Person(Vehicle vehicle){
        System.out.println("person bean created ");
        this.vehicle = vehicle;
    }
    public Person() {
    	
    }
    
//    @Autowired
    private Vehicle vehicle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    /*@Autowired*/
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
