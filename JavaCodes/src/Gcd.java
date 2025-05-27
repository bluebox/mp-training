import java.util.*;
public class Gcd {
    public static int gcd(int firstNum,int secondNum) {
    	if(secondNum==0)return firstNum;
    	return gcd(secondNum,firstNum%secondNum);
    }
	public static void main(String[] args) {
		  Scanner sc=new Scanner(System.in);
		  int firstNum=sc.nextInt();
		  int secondNum=sc.nextInt();
		  System.out.print(gcd(firstNum,secondNum));
	}

}
