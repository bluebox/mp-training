package corejavaday_Two;
import java.util.Scanner;
public class FlourPacker {
    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        int bigCount=inp.nextInt();
        int smallCount=inp.nextInt();
        int goal=inp.nextInt();
        System.out.println(canPack(bigCount,smallCount,goal));
    }

    public static boolean canPack(int bigCount,int smallCount,int goal){
        int remaining=0;
        if(bigCount!=0) {
            remaining = goal % (bigCount * 5);
        }
        if(remaining==0 || goal<smallCount) {
            return true;
        }
        else if(remaining<(smallCount)) {
            return true;
        }
        else {
            return false;
        }
    }
}
