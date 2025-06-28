
public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Palin(121));
		System.out.println(Palin(-121));
		System.out.println(Palin(123));

	}
	
	public static boolean Palin(int num) {
		
		int temp=num,s=0;
		
		if(num<0) {
			num=num*-1;
		}
		
		while(num>0) {
			s=s*10+(num%10);
			num/=10;
		}
		
		if(temp<0) {
			s*=-1;
		}
		return temp==s;
	}

}
