package convertToCentimeter;

public class InchesToCentimeter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		convertToCentimeter(234);
		convertToCentimeter(23,55);
		
	}
	public static void convertToCentimeter(int inches)
	{
		double centimeter=inches*2.54;
		printOutResult(centimeter);
		
	}
	public static void convertToCentimeter(int foot ,int inches)
	{
		double centimeter=((foot*12)+inches)*2.54;
		printOutResult(centimeter);
		
	}
	public static void printOutResult(double centimeter)
	{
		System.out.println("the converions to centimeters is "+centimeter + "cm");
	}

}
