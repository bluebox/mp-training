import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};

        int st = 0;
        int end = arr.length - 1;

        System.out.println(Arrays.toString(arr));

        while (st < end){
            int temp = arr[st];
            arr[st] = arr[end];
            arr[end] = temp;

            st++;
            end--;
        }
        System.out.println(Arrays.toString(arr));
      
    }
}
