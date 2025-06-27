package Day1_prctice;

public class IsVowel {
	// to check whether the given char is vowel or not
	public static void main(String args[]) {
		char a='r';
		if(a==65 || a==69 || a==73 || a==79 || a==85 || a==97 || a== 101 || a==105 || a==111 || a==117 ) {
			System.out.println("The given character i Vowel");
		}
		else {
			System.out.println("Its a consonent");
		}
		
	}
}
