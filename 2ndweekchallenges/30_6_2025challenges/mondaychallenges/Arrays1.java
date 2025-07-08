
import java.util.*;
public class Arrays1 {
    public static void main(String[] args) {
        int[] arr=new int[5];
    Random random=new Random();
    for(int i=0;i<5;i++){
        arr[i]=random.nextInt(100);
        System.out.println(arr[i]);
    }
    System.out.println("arrays before sort "+Arrays.toString(arr));
    Arrays.sort(arr);
    System.out.println("arrays after sort "+Arrays.toString(arr));
    int i=0;
    int j=arr.length-1;
    while (i<j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=arr[i];
        i++;
        j--;

        
    }
    System.out.println("reverse of array"+arr);

    }
    
}
