import java.util.*;
public class Array {

    public static void main(String[] args) {
        int []array = new int[10];  // array of size 10, all elements initialized to 0
        array[0] = 10;
        int len = array.length;
        int arr[] = {1, 5,4,3,2};
        for (int ele : arr) {
            System.out.print(ele+" ");
        }
        System.out.println();
        System.out.println(Arrays.toString(arr));
        // sorting using Arrays
        Arrays.sort(arr);
    }
}
