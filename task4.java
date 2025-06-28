import java.util.Scanner;
public class task4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the byte value:");
        byte b=sc.nextByte();
        System.out.println("enter the short:");
        short s=sc.nextShort();
        System.out.println("enter the integer:");
        int i=sc.nextInt();
        long l=50000+10*(b+s+i);
        System.out.println("the long value is :"+l);
    }
    
}
