package learn;

public class WhileStep1 {
	public static void main(String[] args)
	{
		int num=5;
		while(num<=20) {
			if(isEvenNumber(num)) {
				System.out.println(num);
			}
			num+=1;
		}
	}
	
	public static boolean isEvenNumber(int num) {
		return num%2==0;
	}
}
