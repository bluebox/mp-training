package logical;

public class IsEqual {
	public static void main(String [] args) {
		double parameter1 = 1.3056d;
		double parameter2 = 1.30d;
		boolean result = areEqualByThreePlaces( parameter1,parameter2);
		if(result) {
			System.out.println("same");
		}
		else {
			System.out.println(" not same");
		}
	}
	public static boolean areEqualByThreePlaces(double parameter1,double parameter2 ) {
		if((int)(parameter1 * 1000) == (int)(parameter2 * 1000)) {
			return true;
		}
		return false;
	}
}
