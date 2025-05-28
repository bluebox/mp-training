package SumComparision;

public class SumCompare {

	public static void main(String[] args) {

		int valueOne=3,valueTwo=5,valueCompare=5;
		
		System.out.println(valueOne + " and " + valueTwo +" are " + (hasSameSum(valueOne,valueTwo,valueCompare) ? "equal":" not equal")+ " to " + valueCompare);
	}
	public static boolean hasSameSum(int valueOne,int valueTwo,int valueCompare)
	{
		return ((valueOne + valueTwo) == valueCompare);
	}

}
