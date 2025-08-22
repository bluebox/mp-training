
public class SumLastFirstNum {
   public static int sumFirstAndLastDigit(int number) {
	   if(number<0) {
		   return -1;
	   }
	   else if(number>=0 && number<=9)
	   {
		   return number+number;
	   }
	   else {
		   int l =number%10;
		   int f = number;

	        while (f >= 10) {
	            f /= 10;
	        }
	        return l+f;

	   }
   }
   public static void main(String args[]) {
	   System.out.println(sumFirstAndLastDigit(0));
	   System.out.println(sumFirstAndLastDigit(-109));
	   System.out.println(sumFirstAndLastDigit(948));
	   System.out.println(sumFirstAndLastDigit(4));
	   System.out.println(sumFirstAndLastDigit(37));
	   System.out.println(sumFirstAndLastDigit(-385));
	   System.out.println(sumFirstAndLastDigit(-489));
	   System.out.println(sumFirstAndLastDigit(90));
	   System.out.println(sumFirstAndLastDigit(1));
	   
	   System.out.println(sumFirstAndLastDigit(89));
   }
}
