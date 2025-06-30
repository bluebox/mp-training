import java.util.Scanner;
public class MinElementChallenge {
    public static int[] readIntegers(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the elements seperated by commas : ");
        String input = sc.nextLine();
        String[] stringArray = input.split(",");
        int [] arr=new int[stringArray.length];
        for(int i=0; i<stringArray.length; i++){
            arr[i] = Integer.parseInt(stringArray[i].trim());
        }
        sc.close();
        return arr;
    }
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty or null.");
            return -1;
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
    public static void main(String[] args) {
        int[] arr=readIntegers();
        if(arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        int min = findMin(arr);
        System.out.println("Minimum element is: " + min);
    }
}
