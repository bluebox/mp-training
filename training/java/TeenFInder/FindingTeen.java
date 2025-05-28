package TeenFInder;

public class FindingTeen {

	public static void main(String[] args) {
		
		int valueOne=22,valueTwo=34,valueThree=16;
		String answer=hasTeen(valueOne,valueTwo,valueThree)?" has":" doesn't have";
		System.out.println(valueOne+ ", "+valueTwo+ ", "+valueThree + " "+ answer+ " a teen ");

	}
	public static boolean hasTeen(int valueOne,int valueTwo,int valueThree)
	{
		return (isTeen(valueOne) || isTeen(valueTwo) || isTeen(valueThree));
	}
	
	public static boolean isTeen(int value)
	{
		return (value >= 13 && value <=19);
	}

}
