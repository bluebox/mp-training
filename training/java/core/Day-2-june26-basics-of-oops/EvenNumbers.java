package day_2_june26_basics_of_oops;

public class EvenNumbers {

	public static void main(String[] args) {
		for(int i=5;i<=20;i++) {
			if (isEvenNumber(i)) {
				System.out.println(i);
			}
		}
	}
	public static boolean isEvenNumber(int number) {
		return number%2==0;
	}

}
