package interfaceChallenge;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Mappable> mappables = new ArrayList<>();
		mappables.add( new Building("sydney Town Hall",BuildingType.SPORTS));
		mappables.add(new UtilityLine("College St",UtilityType.FIBER));
		
		for (var m: mappables) {
			Mappable.mapIt(m);
		}
	}
}
