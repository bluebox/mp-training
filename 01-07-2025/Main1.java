package com.prana;


public class Main1 {
    public static void main(String[] args) {

        Mappable building = new Building(
                "Sydney Town Hall", UsageType.GOVERNMENT, Color.RED, PointMarker.STAR);

        Mappable fiberLine = new UtilityLine(
                "College St", UtilityType.FIBER_OPTIC, Color.GREEN, LineMarker.DOTTED);

        System.out.println(" Building JSON:");
        Mappable.mapIt(building);

        System.out.println("\n Utility Line JSON:");
        Mappable.mapIt(fiberLine);
    }
}
