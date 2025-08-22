
public class DigitSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int n=125;
       System.out.println(dSum(n));
       
	}
       
       public static int dSum(int n) {
       int r=0,s=0;
       if(n<0) {
    	   return -1;
       }
       else {
       while(n!=0) {
    	   r=n%10;
    	   s+=r;
    	   n=n/10;
       }
       return s;
       }
	}
}
