package training.java.core.Challenges.ReverseAnArray;

import java.util.Arrays;

public class ReverseAnArray {
    
    private static void reverse(int a[]){
        int x=0;
        int y=a.length-1;
        while(x<y){
            int temp = a[x];
            a[x] = a[y];
            a[y] = temp;
            x++;
            y--;
        }
    }

    // public static void main(String args[]){
    //     int array[] = {1, 2, 3, 4, 5};
    //     reverse(array);
    //     System.out.println(Arrays.toString(array));
    // }
}
