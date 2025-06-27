
public class typeCasting {
	public static void main(String[] args) {
		byte myByte = 10;
		short myShort = 20;
		int myInt = 30;
		float myFloat = 40.0f;
		double myDouble = 50.0d;
		long myLongInt = 60l;
		char myChar = 'A';
		boolean myBoolean = true;
		
		int ByteProductFloat = (int) (myByte * myFloat);
		
		float ShortDivideInt = (float) (myShort/myInt);
		
		double DoubleProductInt = (double) (myDouble * myInt);
		
		System.out.println(ByteProductFloat);
		System.out.println(ShortDivideInt);
		System.out.println(DoubleProductInt);
		
		
		
//		System.out.println("The Basic Primitive Data Types " +
//				"\nByte = "+ myByte +
//				"\nShort = "+ myShort +
//				"\nInt = "+ myInt +
//				"\nFloat = "+ myFloat +
//				"\nDouble = "+ myDouble + 
//				"\nLongInt = "+ myLongInt + 
//				"\nChar = " + myChar + 
//				"\nBoolean = "+ myBoolean
//				);

	}

}
