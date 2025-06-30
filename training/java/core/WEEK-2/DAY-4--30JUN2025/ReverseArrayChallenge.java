import java.util.Arrays;
public class ReverseArrayChallenge {
    public static void reverse(int [] arr){
        int l=0;
        int h=arr.length-1;
        while(l<h){
            int temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
            l++;
            h--;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(arr));
        reverse(arr);
        System.out.println("Reversed array: " + Arrays.toString(arr));
    }
}
