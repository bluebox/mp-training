//printing factors using a method
public class Factors {

	public static void main(String[] args) {
		int number=32;
		if(number<1) {
			System.out.println("Invalid value");
		}else {
		System.out.println("The factors of number "+number +" are "+printFactors(number));
		}
	}
	public static String printFactors(int number) {
		String result="";
		for(int i=1;i<=number;i++) {
			if(number%i==0) {
				result=result+" "+i;
			}
		}
		
		
	return result;
	}

}
