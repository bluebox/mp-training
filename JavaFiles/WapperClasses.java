
public class WapperClasses {
	public static void main(String[] args) {
		byte myMaxByte = Byte.MAX_VALUE, myMinByte = Byte.MIN_VALUE;
		short myMaxShort = Short.MAX_VALUE, myMinShort = Short.MIN_VALUE;
		int myMaxInt = Integer.MAX_VALUE, myMinInt = Integer.MIN_VALUE;
		float myMaxFloat = Float.MAX_VALUE, myMinFloat = Float.MIN_VALUE;
		double myMaxDouble = Double.MAX_VALUE, myMinDouble = Double.MIN_NORMAL;
		boolean myMaxBoolean = Boolean.TRUE, myMinBoolean = Boolean.FALSE;
		
		System.out.println("The Ranges of the Data Types");
		
		System.out.println("Byte = "+myMinByte+" - "+myMaxByte);
		System.out.println("Short = "+myMinShort+" - "+myMaxShort);
		System.out.println("Int = "+myMinInt+" - "+myMaxInt);
		System.out.println("Float = "+myMinFloat+" - "+myMaxFloat);
		System.out.println("Double = "+myMinDouble+" - "+myMaxDouble);
		System.out.println("Boolean = "+myMinBoolean+" - "+myMaxBoolean);
		
	}

}
