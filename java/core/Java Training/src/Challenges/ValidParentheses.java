package challanges;
import java.util.*;

public class ValidParentheses {
	
	public static boolean isParentheses(String s) {
		Stack<Character> stack=new Stack<>();
		int n=s.length();
		for(int i=0;i<n;i++) {
			
			char ch=s.charAt(i);
			if(ch=='(' || ch=='<' || ch=='{' || ch=='[') {
				stack.push(ch);
			}
			else {
				if(stack.isEmpty()) {
					return false;
				}
				
				char top = stack.peek();

                if ((top == '(' && ch == ')') || (top == '[' && ch == ']') ||
                    (top == '{' && ch == '}') || (top == '<' && ch == '>')) {
                	
                    stack.pop();
                } 
                else {
                    return false;
                }
//				if(!stack.empty() && ((stack.peek()=="(" && s.charAt(i)==')') || (stack.peek()=="<" && s.charAt(i)=='>') || 
//						(stack.peek()=="{" && s.charAt(i)=='}') || (stack.peek()=="[" && s.charAt(i)==']'))) {
//					stack.pop();
//				}
//				else {
//					return false;
//				}
			}
		}
		return stack.empty();
	}
	
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the parentheses pattern : ");
	String s=sc.nextLine();
	System.out.println("The pattern given : "+s);
	s=s.replace("<h1>","<");
	s=s.replace("</h1>",">");
	System.out.println("The pattern modified : "+s);
	if(isParentheses(s)) {
		System.out.println("The parrnthese pattern is valid");
	}
	else{
		System.out.println("The parrnthese patten is not valid");
	}
	sc.close();
	}	
}
