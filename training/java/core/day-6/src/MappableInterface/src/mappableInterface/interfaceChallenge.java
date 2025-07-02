package mappableInterface;

public class interfaceChallenge {
	public static void main(String[] args) {
		Building building = new Building("Sydney Town Hall", UsageType.GOVERNMENT, pointMarker.RED_STAR);
        UtilityLine line = new UtilityLine("College St", UtilityType.FIBER_OPTIC, lineMarker.GREEN_DOTTED);

        mappable.mapIt(building);
        mappable.mapIt(line);
	}
}
