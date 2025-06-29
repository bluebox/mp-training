package june26_methods;
import java.util.Scanner;
public class CatPlaying {
	
	public static boolean catPlaying(boolean summer,int temp) {
		if(summer==true) 
			return (temp>=25 && temp<=45);
		else
			return (temp>=25 && temp<=35);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the whether the season is summer and its temperature it is playing");
		boolean summer=sc.nextBoolean();
		int temp=sc.nextInt();
		System.out.println("result : "+catPlaying(summer,temp));
		sc.close();
			
	}

}
