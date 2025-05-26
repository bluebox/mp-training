package learn;

public class WhileStep2 {
	public static void main(String[] args)
	{
		int countOdd=0,countEven=0,num=5;
		while(true) {
			if(isEvenNumber(num)) {
				System.out.println(num);
				countEven+=1;
			}
			else {
				countOdd+=1;
			}
			num+=1;
			System.out.println("total odd numbers"+countOdd);
			System.out.println(num);
			if(countEven==5) {
				break;
			}
		}
	}
	public static boolean isEvenNumber(int num) {
		return num%2==0;
	}
}
