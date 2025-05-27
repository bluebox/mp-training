public class OperatorPrecedence {
	 public static void main(String[] args) {
	        int a = 5, b = 10, c = 15, d = 20;
	        
	        int result = ((++a * b-- + c / (d - a) % 3) << 2 & 8 | 1) == 1 ? 100 : 200;

	        System.out.println("Result: " + result);
	    }
}