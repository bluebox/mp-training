
public class InterfaceChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        Mappable building = new Building("Sydney Town Hall", "GOVERNMENT",
		                                   Color.RED, PointMarker.STAR);
		        Mappable line = new UtilityLine("College St", "FIBER_OPTIC",
		                                        Color.GREEN, LineMarker.DOTTED);

		        Mappable.mapIt(building);
		        Mappable.mapIt(line);
		    }
	}

