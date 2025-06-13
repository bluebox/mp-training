import java.util.*;
public class StarPattern {
    public static void printSquarePattern(int num) {
    	if(num<5) {
    		System.out.print("invalid Case");
    		return ;
    	}
    	for(int i=0;i<num;i++) {
    		for(int j=0;j<num;j++) {
    			if(i==0 || i==num-1 || j==0|| j==num-1|| i==j|| j==num-1-i)System.out.print('*');
    			else System.out.print(' ');
    		}
    		System.out.println();
    	}
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		printSquarePattern(num);
	}

}
