import java.util.*;

public class EvenNumber {
	public static void isEvenNumber() {
		int counter=0,c=0,i=5;
		while(i<=20 && counter < 5){
			if(i%2==0) {
				counter++;
				System.out.print(i+" ");
			}else {
				c++;
			}
			i++;
		}
		System.out.print("No:of Even numbers "+counter+" and No:of Odd numbers "+c+" in the range of (5,20) inclusive");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		isEvenNumber();
	}

}
