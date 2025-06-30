
public class NATO {

	public static void main(String[] args) {
		
		String str="ABC";
		String ans="";
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='A') {
				ans+="Able ";
			}
			if(str.charAt(i)=='B') {
				ans+="Baker";
			}
			if(str.charAt(i)=='C') {
				ans+="Charlie ";
			}
			if(str.charAt(i)=='D') {
				ans+="Dog ";
			}
			if(str.charAt(i)=='E') {
				ans+="Easy ";
			}
		}
		System.out.println(ans);

	}

}
