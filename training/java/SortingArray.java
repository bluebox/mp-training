import java.util.*;
public class SortingArray {
    public static void main(String[] args) {
        int[] array = new int[10];
        for(int i=0;i<10;i++){
            array[i]=(int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        for(int i=0;i<10/2;i++){
            int temp = array[i];
            array[i] = array[10-i-1];
            array[10-i-1] = temp;
        }
        System.out.println(Arrays.toString(array));
    }
}
