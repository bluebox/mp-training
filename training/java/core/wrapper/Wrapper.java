package wrapper;

public class Wrapper {
	public static void main(String[] args) {
			
	//		byte primitive data type
			byte a=1;
			
	//		byte primitive data type to Byte Wrapper class
			Byte A=Byte.valueOf(a);
			
	//		int primitive data type
			int b=10;
			
	//		int primitive data type to Integer Wrapper class
			Integer B=Integer.valueOf(b);
			
	//		float primitive data type
			float c=8.2f;
			
	//		Autoboxing float primitive data type to Float Wrapper class
			Float C=c;
			
	//		double primitive data type
			double d=16.8;
			
	//		Autoboxing double primitive data type to Double Wrapper class
			Double D=d;
			
	//		char primitive data type
			char e='a';
			
	//		Autoboxing char primitive data type to Character Wrapper class
			Character E=e;
			
	//		Printing the values
	//		All these values are Autoboxed because the values are converted into object
			System.out.println("Byte object : "+A);
			System.out.println("Integer object : "+B);
			System.out.println("Float object : "+C);
			System.out.println("Double object : "+D);
			System.out.println("Character object : "+E);
			
	//		Unboxing all the Autoboxed objects into primitive data types
			byte a1=A;
			int b1=B;
			float c1=C;
			double d1=D;
			char e1=E;
	//		These are all unboxed because all the objects are converted into primitive data types
			System.out.println("byte value : "+a1);
			System.out.println("int value : "+b1);
			System.out.println("float value : "+c1);
			System.out.println("double value : "+d1);
			System.out.println("char value : "+e1);
	}
}
