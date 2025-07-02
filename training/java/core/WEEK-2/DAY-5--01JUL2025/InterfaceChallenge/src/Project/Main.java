package Project;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Sydney Town Hall", UsageType.GOVERNMENT);
        UtilityLine utilityLine = new UtilityLine("College St", UtilityType.FIBER_OPTIC);

        Mappable.mapIt(building);
        Mappable.mapIt(utilityLine);
    }
}


