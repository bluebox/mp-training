import java.util.Arrays;
import java.util.Random;

public class Array1 {
    public static void main(String args[]) {
    	int a[] = new int[] {10,44,66,77,98};
    	System.out.print("Array Elements are ");
   	    for(int i=0;i<a.length;i++) {
   		 System.out.print(a[i]+" ");
     	 }
     	 System.out.println();
	    System.out.println("Reversed Array Elements are ");
	 
	    for(int j=a.length-1;j<=0;j--) {
		 System.out.println(a[j]);
	     }
    	
    	
    	 Random random = new Random(); 
    	 int[] array = new int[5];
    	 for(int i=0;i<array.length;i++) {
    		 array[i]=random.nextInt(50);
    	 }
    	 
    	 System.out.print("Array Elements before sort are ");
    	 for(int i=0;i<array.length;i++) {
    		 System.out.print(array[i]+" ");
    	 }
    	 System.out.println();
    	 System.out.println("Sorted Array Elements are ");
    	 Arrays.sort(array);
    	 for(int i=0;i<array.length;i++) {
       		 System.out.print(array[i]+" ");
         	 }
    }
   
}
