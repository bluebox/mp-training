package Day2_26_06;

public class GCD {
	public static void main(String args[]) {
		int first=25,second=625;
		if(first>second) {
		    while (second != 0) {
		        int temp = second;
		        second = first % second;
		        first = temp;
		    }
		    System.out.println(first);
		}
		else {
			 while (first != 0) {
			        int temp = first;
			        first = second % first;
			        second = temp;
			    }
			    System.out.println(second);
		}
	}
}
