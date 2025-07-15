
import java.util.Scanner;
import java.util.Collections;

public class binarybit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print(" enter number : ");
        int count = scanner.nextInt();
      
        int totalSetBits = 0;
        int czerobits=0;

        for (int i = 1; i <= count; i++) {
           
            totalSetBits +=countSetBits(i) ;
            czerobits+=countZeroBits(i);
            System.out.println(i+"="+binnumber(i));


        }

        System.out.println("Total set bits in all entered numbers: " + totalSetBits);
        System.out.println("Total zero bits in all entered numbers: " + czerobits);

    }


    public static int countSetBits(int num) {
        int count =0 ;
        while (num > 0) {
            if(num%2==1){
            count++;
            
             
        }
        num=num/2;
       
    }
     return count;
}

     public static int countZeroBits(int num) {
        int count =0 ;
        while (num > 0) {
            if(num%2==0){
            count++;
            
            }
            num=num/2;
           
        }
        return count;

    }
    public static String binnumber(int num) {
        String str ="" ;
        while (num > 0) {
            if(num%2==0){
            str+="0";
            
            }
            else{
                str+="1";
            }
            num=num/2;
            
        }
        StringBuilder sb=new StringBuilder(str);
        return sb.reverse().toString();
    
    }
}