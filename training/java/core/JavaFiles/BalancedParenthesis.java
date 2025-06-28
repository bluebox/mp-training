import java.util.Scanner;
import java.util.Stack;


public class BalancedParenthesis {
	
	public static boolean check(String str) {
		Stack<Character> st=new Stack<>();
		
		
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i);
			if(str.charAt(i)=='(' || str.charAt(i)=='{' || str.charAt(i)=='[') {
				st.push(str.charAt(i));
			}else {
				if (st.isEmpty()) {
					return false;
				}
					char top =st.pop();
					if(
					ch==')' && top!='('  || 
					ch==']' && top!='[' ||
					ch=='}' && top!='{' ) {
				return false;
			}
		}
	}
		return st.isEmpty();
	}
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);	
		System.out.println("Enter a Stirng of Parenthsis :");
		String str=sc.nextLine();
		System.out.println(check(str));
	}

}
