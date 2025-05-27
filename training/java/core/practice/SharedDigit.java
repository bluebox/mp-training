
public class SharedDigit {

	public static void main(String[] args) {
		//share a digit and should be in the range 10-99
		int a=23,b=43;
		if(hasSharedDigit(a,b)) {
			System.out.println("The given numbers "+a+" and "+b+" share a digit.");
		}
	}

public static boolean hasSharedDigit(int a,int b) {
		boolean result=false;
		int a1,a2,b1,b2;
		if(((a>9)&(a<100))&((b>9)&(b<100))){
			
			
			a1=a%10;
			a2=((a/10)%10);
			b1=b%10;
			b2=((b/10)%10);
			if(a1==b1 || a1==b2) {
				result=true;
			}
			
			else if(a2==b1 || a2==b2) {
				result=true;
			}
			else {
				result=false;
				
			}
		}
		return result;
}	
}