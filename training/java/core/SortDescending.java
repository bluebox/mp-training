import java.util.Arrays;

public class SortDescending {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,36,9,4,5};

        

        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);

        int st = 0;
        int end = arr.length - 1;

        //System.out.println(Arrays.toString(arr));

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
