package training_java.May23;

public class WordConversion {
public static String convertNumber(int number){
	String[] units= {"","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
	String[] tens= {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninty"};
	String word="";
	int n=number;
	while(n>0) {
			int value=n%1000;
			String temp="";
			if(value>=100) {
				temp=temp+units[value/100]+"Hundred";
				//System.out.print(value+temp);
				value=value%100;
			}
			if(value>=20) {
				temp=temp+tens[value/10];
				value=value%10;
			}
			if(value>0) {
				temp=temp+units[value];
			}
	n=n/1000;
	word=temp;
	}
	return word;		
}
public static void main(String[] args) {
	System.out.println(convertNumber(991));
	
}
}
