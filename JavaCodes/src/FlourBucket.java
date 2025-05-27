import java.util.*;
public class FlourBucket {
    public static boolean canPack(int bigBuck,int smallBuck,int goal) {
    	if(bigBuck==goal)return true;
    	if(smallBuck>=goal)return true;
    	if(bigBuck<0)return false;
    	while(goal>=5 && bigBuck>0) {
    		goal-=5;
    		bigBuck-=1;
    	}
    	return smallBuck>=goal?true:false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int bigBuck=sc.nextInt();
		int smallBuck=sc.nextInt();
		int goal=sc.nextInt();
		System.out.println(canPack(bigBuck,smallBuck,goal));
	}

}
