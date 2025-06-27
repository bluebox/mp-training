package Day2_26_06;

public class GCD {
	public static void main(String args[]) {
		int first=25,second=15;
		 if (first < 10 || second < 10) {
		        System.out.println("Invalid");
		    }else {

		    while (second != 0) {
		        int temp = second;
		        second = first % second;
		        first = temp;
		    }

		    System.out.println(first);
	}
	}
}
