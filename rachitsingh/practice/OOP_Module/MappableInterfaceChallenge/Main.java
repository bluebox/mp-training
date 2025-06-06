package MappableInterfaceChallenge;

import MappableInterfaceChallenge.enums.LineMarker;
import MappableInterfaceChallenge.enums.PointMarker;
import MappableInterfaceChallenge.enums.UsageType;
import MappableInterfaceChallenge.enums.UtilityType;

public class Main {
	public static void main(String[] args) {
		Building newBuild1 = new Building("Department of Trade and Taxes", UsageType.GOVERNMENT, PointMarker.RED_STAR);
		Building newBuild2 = new Building("Optival Health Solutions Pvt. Ltd.", UsageType.BUSINESS,
				PointMarker.DIAMOND);
		UtilityLine newUtility = new UtilityLine("College St.", UtilityType.FIBER_OPTIC, LineMarker.GREEN_DOTTED);

		Mappable.mapIt(newBuild1);
		Mappable.mapIt(newBuild2);
		Mappable.mapIt(newUtility);
	}
}
