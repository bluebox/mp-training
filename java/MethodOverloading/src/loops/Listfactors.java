package loops;

public class Listfactors {
	public static void main(String [] args) {
		int number= 12;
		int counter ;
		System.out.println("the factors are:");
			printFactor(number);
	}

	public static void printFactor( int number) {
		//System.out.println(1);
		for(int counter=1; counter<number; counter++) {
		if(number % counter == 0) {
			System.out.println(counter);
		}
	}
		System.out.println(number);

}
}
