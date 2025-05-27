import java.util.*;
public class PlayingCat {
    public static void isPlaying(boolean summer,int temp) {
    	if(summer && (temp>=25 && temp<=45)) {
    		System.out.println("cat is playing");
    		return ;
    	}
    	if(!summer && (temp>=25 && temp<=35)) {
    		System.out.println("cat is playing");
    		return ;
    	}
    	System.out.println("cat is not playing");
    }
	public static void main(String[] args) {
		System.out.println("enter the isSummer and temp");
		Scanner sc=new Scanner(System.in);
		boolean isSummer=sc.nextBoolean();
		int temp=sc.nextInt();
		isPlaying(isSummer,temp);
	}

}
