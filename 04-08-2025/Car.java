package mymaven;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary; 

@Component
@Primary 
public class Car implements Vehicle {

    public String getName() {
        return "Car";
    }
}
