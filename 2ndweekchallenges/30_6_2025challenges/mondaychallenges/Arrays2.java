import java.util.*;
public class Arrays2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter tha arrays");
        String arr=sc.next();
        String[] num_arr=arr.split(",");
        int min=Integer.MAX_VALUE;
        for(int i=0;i<arr.length();i++){
            int value=Integer.parseInt(num_arr[i]);
            if(min>value){
                min=value;
            }
            
        }

        System.out.println("minimum value is "+min);
    }
    
}
