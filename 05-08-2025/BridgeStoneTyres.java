package com.example.implementations;

import com.example.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres {

    public String rotate(){
        return "Vehicle moving with BridgeStone tyres";
    }
}