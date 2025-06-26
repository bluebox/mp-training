public class TypeConversions { 
	
	
	public static void main(String args[]) {
		byte b=(byte)128;
		System.out.println(b);
		
		//int t=(int)21474836478;
		//gives comiplation error,because here by default literal is treated int here and it is out of bounds
		//System.out.println(t);
		
		short s=(short)35000;
		System.out.println(s);
		int t=(int)21474836478L;
		System.out.println(t);
		
		short s1=(int)38.14;
		System.out.println(s1);
		
		float a=(float)3.14;
		System.out.println(a);
		
		int c=(int)a;
		System.out.println(c);
		
		float d=2147483648L;
		System.out.println(d);
		
		long e=(long)3.14;
		System.out.println(e);
	}

}