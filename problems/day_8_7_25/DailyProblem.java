package day_8_7_25;
import java.util.*;


public class DailyProblem {

	
	static int precedence(char ch) {
		switch(ch) {
		case '+':case '-':return 1;
		case '*':case '/':return 1;
		default:return -1;			
		}
	}
	
	public static String infixtoPrefix(String s) {
		
		// changing the brackets
		StringBuilder infix=new StringBuilder(s);
		infix.reverse();
		String updatedinfix=infix.toString();
		char [] chars=updatedinfix.toCharArray();
		for(int i=0;i<updatedinfix.length();i++) {
			if(updatedinfix.charAt(i)=='(') {
				chars[i]=')';
			}else if(chars[i]==')') {
				chars[i]='(';
			}
		}
		
		String update=new String(chars);
		
		
		Stack<Character> st=new Stack<>();
		StringBuilder response=new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(update.charAt(i)>='A' && update.charAt(i)<='Z') {
			response.append(update.charAt(i));
			}else if(update.charAt(i)=='(') {
				st.push('(');
			}else if(update.charAt(i)==')') {
				while(!st.isEmpty() && st.peek() != '(') {
					response.append(st.pop());
				}
				st.pop();
			}else {
				while(!st.isEmpty() && precedence(update.charAt(i))<=precedence(st.peek())) {
					response.append(st.pop());
				}
				st.push(update.charAt(i));
			}
		}
		
		
		while(!st.isEmpty()) {
			response.append(st.pop());
		}
		
		return new StringBuilder(response.toString()).reverse().toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String exp="((A-B)/C)*(D-E)";
       String prefix=infixtoPrefix(exp);
       System.out.println(exp+" "+prefix);
	}

}
