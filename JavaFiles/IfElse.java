
public class IfElse {
	public static void main(String[] args) {
		double a = 20.00;
		double b = 80.00;
		double c = (a+b)*100.00;
		
		double r = c/a;
		
		System.out.println(r);
		
		if(r==0.00) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
		
		boolean var= false;
		
		if(a>b) {
			System.out.println("Valid - a>b");
		}else if(a==b) {
			System.out.println("Equal - a==b");
		}else if(var = true) {
			System.out.println("if() required a boolean value");
		}
		
	}

}
