package day6.printEqual;

public class IsEqual {
public static void isEquals(int t1,int t2,int t3) {
	if(t1==0 || t2==0 || t3==0) {
		System.out.println("invalid value");
		return ;
}
	else if (t1==t2 && t2==t3) {
		System.out.println("All Numbers are Equal");
	}
	else if(t1!=t2 && t1!=t3 && t2!=t3 )
		System.out.println("All numbers are different");
	else {
		System.out.println("Neither all are equal or different");
	}
}

}

