
public class LastDigit {

	public static void main(String[] args) {
		int a=4,b=44,c=54;
		if(hasSameLastDigit(a,b,c)) {
			System.out.println("The given three numbers has same last digit and are in the given range.");
		}
		else {
			System.out.println("The numbers either do not have same last digits or not in the given range.");
		}

	}
	public static boolean hasSameLastDigit(int a,int b, int c) {
		boolean result=false;
		if(isValid(a) & (isValid(b)& isValid(c))) {
			if((a%10)==(b%10)) {
				if((a%10)==(c%10)){
					result=true;
				}
			}
		}		
		return result;
	}
	public static boolean isValid(int number) {
		return (number<10 || number>999)?false:true;
	}

}
