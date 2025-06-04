import java.util.Scanner;
import java.util.Arrays;
public class ArrayChallenege{
    public static void main (String[] args){
        int[] arr=readArray();
        System.out.println("Array before sorting:"+Arrays.toString(arr));
        
        Arrays.sort(arr);
        System.out.println("Array after sorting:");
        System.out.println(Arrays.toString(arr));
        System.out.println("Array after reversing:"+Arrays.toString(reverse(arr)));
        System.out.println("the minimum element in the array is "+findMin(arr));

    }
        

        public static int[] reverse(int[]array){
            int n=array.length;
            int[] reverseArray=new int[n];
            for(int i=0;i<n;i++){
                reverseArray[i]=array[n-i-1];
                
            }
            return reverseArray;
        }
    
    public static int findMin(int[] array){
        int min=Integer.MAX_VALUE;
        for(int i:array){
            if (i<=min){min=i;}
        }
        return min;
        }
    public static int[] readArray(){

        Scanner scanner = new Scanner(System.in);
        String S=scanner.nextLine();
        String[] words=S.split(",");
        int[] array=new int[words.length];
        int j=0;
        for(String i:words){
            array[j]=Integer.parseInt(i);
            j++;

        }
        return array;
    }
}


    
