package cook;

public class Main {

	public static void main(String[] args) {
		Lasagna lasagna = new Lasagna();
		System.out.println(lasagna.exceptedMinitesInOven());
		System.out.println(lasagna.remainingMinutesInOven(30));
		System.out.println(lasagna.preparationTimeInMinutes(2));
		System.out.println(lasagna.totalTimeInMinutes(1, 30));

	}

}
