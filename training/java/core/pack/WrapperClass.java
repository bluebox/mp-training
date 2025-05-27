public class WrapperClass {

	public static void main(String args[]) {
		Integer var1=Integer.valueOf(10); // autoboxing
		int var2=Integer.parseInt("10"); //unboxing
		int var3=var1.intValue(); //unboxing 
		
		Integer var4=Integer.valueOf(10);
		Integer var0=new Integer(10);
		
		System.out.println("is var1==var4 "+(var1==var0));
		System.out.println("is var2==var3 "+(var2==var3));
		
		//double class 
		Double d1=new Double(3.14);
		Double d4=new Double(3.14);
		Double d2=Double.valueOf(3.14); //autoboxing
		double d3=d2.doubleValue(); //unboxing 
		System.out.println(d2+" "+d3);
		System.out.println((d1==d4));
		
		
	}
	
}