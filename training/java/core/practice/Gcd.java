import java.util.*;

public class Gcd {
	
    public static int gcd(int first,int second) {
    	if(second==0)return first;
    	return gcd(second,first%second);
    }
    
	public static void main(String[] args) {
		  Scanner sc=new Scanner(System.in);
		  int first=sc.nextInt();
		  int second=sc.nextInt();
		  sc.close();
		  System.out.print(gcd(first,second));
	}

}