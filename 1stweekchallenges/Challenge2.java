
import java.util.Scanner;
public class Challenge2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        byte b=sc.nextByte();
        short s=sc.nextShort();
        int i=sc.nextInt();
        long l=50000+10*(b+s+i);
        System.out.println(l);
    }
}
