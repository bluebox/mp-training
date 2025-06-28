import java.util.ArrayList;
import static java.util.Collections.max;
import static java.util.Collections.min;
import java.util.HashSet;
import java.util.Scanner;
public class missele {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(" enter no of elemnts u want in array:");
        int n=sc.nextInt();
        System.out.println("enter elements in array:");
        ArrayList<Integer> arr=new ArrayList<>();
        for (int idx = 0; idx < n; idx++) {
            int e=sc.nextInt();
            arr.add(e);
            
        }
        System.out.println(arr);
        int mine=min(arr);
        int maxe=max(arr);
      
        HashSet<Integer> arr2 = new HashSet<>(arr);
        int sum1=0;
        for (int num : arr2) {
            sum1 += num;
        }
        int sum2=0;
        for (int i=mine;i<=maxe;i++){
            sum2+=i;
        }
        System.out.println("missing value "+(sum1-sum2));
    }

        
    }
    

