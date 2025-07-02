package day5;

import java.util.ArrayList;
import java.util.List;

public class Mappable_Main {

    public static void main(String[] args) {

        List<Mappable> mappables = new ArrayList<>();

        // Buildings in Hyderabad
        mappables.add(new Building("Secretariat", UsageType.GOVERNMENT));
        mappables.add(new Building("Prasad's IMAX", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Uppal Cricket Stadium", UsageType.SPORTS));

        // Utility lines in Hyderabad
        mappables.add(new UtilityLine("Madhapur Water Line", UtilityType.WATER));
        mappables.add(new UtilityLine("Outer Ring Road", UtilityType.FIBER_OPTIC));

        for (Mappable m : mappables) {
            Mappable.mapIt(m);
        }
    }
}
