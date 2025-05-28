package samplecodes;

public class DigitsIntoWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=1234;
		System.out.println(numberToWords(n));
		
	}
	public static String numberToWords(int n) {
		StringBuilder sb=new StringBuilder();
		String s1=Integer.toString(n);
		for(int i=0;i<s1.length();i++) {
			char ch=s1.charAt(i);
			switch(ch) {
			case '1': 
				sb.append("One ");
				break;
			case '2':
				sb.append("Two ");
				break;
				
			case '3':
				sb.append("Three ");
				break;
			case '4':
				sb.append("Four ");
				break;
			case '5':
				sb.append("Five ");
				break;
			case '6':
				sb.append("Six ");
				break;
			case '7':
				sb.append("Seven ");
				break;
			case '8':
				sb.append("Eight ");
				break;
			default:
				sb.append("Nine ");
				break;
			}
		}
		return sb.toString();
	}

}
