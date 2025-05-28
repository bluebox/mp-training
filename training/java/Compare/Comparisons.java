package Compare;

public class Comparisons {

	public static void main(String[] args) {
		double valueX=23.3458,valueY=23.3457;
		boolean isSame= areTheyEqualByThreeDecimalPlaces(valueX,valueY);
		System.out.println(valueX +" and "+valueY + ( isSame ?" same":" not same"));

	}
	
	public static boolean areTheyEqualByThreeDecimalPlaces(double valueX,double valueY)
	{
		
		
		return( (int) (valueX*1000) == (int) (valueY*1000));
		
	}

}
