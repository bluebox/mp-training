import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
public class ArraysChallenge {
    public static void generateRandomIntegers(Integer [] arr, int n){
        Random random=new Random();
        for(int i=0; i<n; i++){
            arr[i]=random.nextInt(100); // Generates random integers between 0 and 99
        }
    }
    public static void main(String[] args) {
        int n=5;
        Integer [] arr=new Integer[n];
        generateRandomIntegers(arr, n);
        System.out.println(Arrays.toString(arr));
        
        System.out.println("Sorted Array:");
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));
    }
}
