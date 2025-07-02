package Day_1_7_25.maxsum;
import java.util.*;

public class MaxSumOfCircularArray {
   static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int length=sc.nextInt();
       int arr[]=new int[length];
       for(int i=0;i<length;i++) {
    	   arr[i]=sc.nextInt();
       }
       int maxsum=0;
       for(int i=0;i<length-1;i++) {
    	   maxsum+=arr[i];
       }
       //sliding widow
       //kadens algo
      
       for(int i=1;i<length;i++) {
    	   int sum=0;
    	   for(int j=i;j<length;j++) {
    		   sum+=arr[j];
    	   }
    	   
    	   for(int k=0;k<i-1;k++) {
    		   sum+=arr[k];
    	   }
    	   
    	   maxsum=Math.max(maxsum, sum);
       }
       
       System.out.println(maxsum);
       
	}

}
