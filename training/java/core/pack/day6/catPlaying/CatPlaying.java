package day6.catPlaying;
import java.util.*;

public class CatPlaying {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t=0,temp;
		System.out.println("Enter it is summer or not and temperature -1 to quit");
		String s;
	try{
		while(true) {
			System.out.println("enter the temperature");
			temp=sc.nextInt();
			if(t==-1)
				break;
			System.out.println("enter is it summer T/F");
			s=sc.next();
//			if(!s.matches("[TFtf]"))
//				throw new Exception();
			
			
			
			if(s.toLowerCase().equals("t"))
				System.out.println("is cat playing "+PlayingCat.isCatPlaying(true,temp));
			else 
				System.out.println("is cat playing "+PlayingCat.isCatPlaying(false,temp));
			
			System.out.println("------------------------------------");
		}
	}
	catch(Exception e){
	System.out.println("invalid input "+ e);
	}
	}
}
