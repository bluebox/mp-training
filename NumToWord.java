import java.util.Scanner;

public class NumToWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr= {"ONE", "TWO", "THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
		
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter a Number: ");
		
		int num=sc.nextInt();
		
		if(num>0 && num<10) {
			System.out.println(arr[num-1]);
		}
		else {
			System.out.println("Other");
		}

	}

}
