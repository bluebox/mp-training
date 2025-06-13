import java.util.*;
public class BrakingDog {
    public static boolean shouldWakeUp(boolean isBraking,int time) {
    	if(!isBraking)return false;
    	if(time>=8 && time<=22)return false;
    	return true;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		boolean isBraking=sc.nextBoolean();
		int time=sc.nextInt();
		boolean wakeUp=shouldWakeUp(isBraking,time);
		if(wakeUp) {
			System.out.println("time to wakeup");
		}
		else System.out.print("don't wakeup now");
	}

}
