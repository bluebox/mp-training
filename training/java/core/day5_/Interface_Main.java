package com.day5_;

import java.util.ArrayList;
import java.util.List;

public class Interface_Main {

    public static void main(String[] args) {

        List<Interface_Mappable> mappables = new ArrayList<>();

        mappables.add(new Interface_Building("Nelofer Hospital", UsageType.GOVERNMENT));
        mappables.add(new Interface_Building("Prasads IMAX",
                UsageType.ENTERTAINMENT));
        mappables.add(new Interface_Building("Uppal Stadium", UsageType.SPORTS));

        mappables.add(new Interface_UtilityLine("Hi-Tech City", UtilityType.FIBER_OPTIC));
        mappables.add(new Interface_UtilityLine("Konda Pochamma Reservoir", UtilityType.WATER));

        for (var m : mappables) {
            Interface_Mappable.mapIt(m);
        }
    }
}