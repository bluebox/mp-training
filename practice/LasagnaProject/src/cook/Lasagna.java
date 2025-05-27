package cook;

public class Lasagna {
	private final int ovenTime =40; // according to cooking book
	
	int exceptedMinitesInOven() {
		return ovenTime;
	}
	int remainingMinutesInOven(int actualTime) {
		int remTime = exceptedMinitesInOven() - actualTime;
		return remTime > 0?remTime:0;
	}
	int preparationTimeInMinutes(int layers) {
		return layers*2;
	}
	int totalTimeInMinutes(int layers,int actualTime) {
		return actualTime + preparationTimeInMinutes(layers);
	}
}
