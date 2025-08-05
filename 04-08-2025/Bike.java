package mymaven;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {

    public String getName() {
        return "Bike";
    }
}
