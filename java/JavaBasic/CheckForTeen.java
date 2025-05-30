package logical;

public class CheckForTeen {
	public static void main(String [] args) {
		int ageOne = 12;
		int ageTwo = 71;
		int ageThree =28;
		boolean result = hasTeen(ageOne, ageTwo, ageThree);
		if(result) {
			System.out.println("Has Teen");
		}
		else {
			System.out.println(" No Teen");
		}
	}
	public static boolean hasTeen(int ageOne, int ageTwo, int ageThree){
		if((ageOne>=13 && ageOne<=19) || (ageTwo>=13 && ageTwo<=19) || (ageThree>=13 && ageThree<=19)) {
			return true;
		}
		return false;
	}
}
