
public class SharedDigitBetweenTwoNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if( hasSharedDigit(12,23)) {
		System.out.println("Shared Digit is Present");}
		else {
		System.out.println("No Shared Digit ");
		}

		
		if( hasSharedDigit(9,99)) {
			System.out.println("Shared Digit is Present");}
			else {
			System.out.println("No Shared Digit ");
			}
		
		if( hasSharedDigit(17,79)) {
			System.out.println("Shared Digit is Present");}
			else {
			System.out.println("No Shared Digit ");
			}
		
		if( hasSharedDigit(33,93)) {
			System.out.println("Shared Digit is Present");}
			else {
			System.out.println("No Shared Digit ");
			}
	}
     public static boolean hasSharedDigit(int m,int n) {
    	 int r=0,s=0,t=0;
    	 if(n<0 || m<0) {
    		 return false;
    	 }
    	 else {
    		 while(n!=0 && m!=0) {
    			 r=m%10;
    			 s=n%10;
    			 if(m!=n) {
    				 n=n/10;
    			 }
    			 else {
    				 return false;
    			 }
    		 }
    		 return true;
    	 }
     }
}
