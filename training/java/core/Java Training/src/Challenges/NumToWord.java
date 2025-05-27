package Challenges;
import java.util.Scanner;
public class NumToWord {
	
	private static String[] units= {
			"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen",
			"Fifteen","Sixteen","Seveteen","Ninteen"
		};
	private static String[] tens= {
			"","","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninty"
	};
	
	public static String convertToWord(int n) {
		if(n<1 || n>1000) {
			return "Invalid input";
		}
		if(n==1000) {
			return "One Thousand";
		}
		String res="";
		if(n>=100) {
			res+=units[n/100]+ " Hundred";
			n%=100;
			if(n!=0) {
				res+=" and ";
			}
		}
		if(n>=20) {
			res+=tens[n/10];
			if(n%10!=0) {
				res+=" - "+units[n%10];
			}
		}
		else if(n>0) {
			res+=units[n];
		}
		return res;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the number between 1 to 1000 : ");
		int n=scanner.nextInt();
		String word=convertToWord(n);
		System.out.println("The "+n+" word form : "+word);
		scanner.close();

	}

}
