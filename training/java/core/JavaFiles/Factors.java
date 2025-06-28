
public class Factors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Factor(8);
		Factor(-2);
	}
	
	public static void Factor(int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
            return;
        }

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.println(i);
            }
        }
    }

}
