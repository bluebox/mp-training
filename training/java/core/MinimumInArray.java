import java.util.Arrays;

public class MinimumInArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};

        int st = 0;
        int end = arr.length - 1;

        System.out.println(Arrays.toString(arr));

        int mini = Integer.MAX_VALUE;

        for(int i =0; i<arr.length; i++){
            if (mini > arr[i]){
                mini = arr[i];
            }
        }
        System.out.println("minimum element in the array : "+mini);
      
    }
}
