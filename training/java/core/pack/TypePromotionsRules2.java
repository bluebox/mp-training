public class TypePromotionsRules2 {

	
	public static void main(String args[]) {
		char a='B';
		int b=35+a;
		System.out.println(b);
		
		int c=35;
		double d=35+6.5;
		System.out.println(d);
		
		long l=535L;
		float f=65.6f;
		System.out.println(l+f);
		
		//Only case where promotion rule not followed is 
		int result=60;
		result+=5.5;
		System.out.println(result);
		
		char z='z';
		int num=z;
		System.out.println(a+z);//sum of characters give sum of theri ascii numbers
		//because they are stored as two byte numbers in RAM
		
		//int see=60+5.5;  this will give the error
		System.out.println(num);
		
	}
}