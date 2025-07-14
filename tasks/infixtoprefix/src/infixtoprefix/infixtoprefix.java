package infixtoprefix;

import java.util.Scanner;
import java.util.Stack;

public class infixtoprefix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter equation:");
        String s = sc.nextLine();
        if (issvalidexpression(s)){
        System.out.print(in_to_pre(s));
;
        }
        else{
            System.out.println("invalid expression");
        }

    }
    public static boolean issvalidexpression(String s){
        int b=0;
        if (isoperator(s.charAt(0))|| isoperator(s.charAt(s.length()-1))){
        	return false;
        }
        if (s.contains("()")) {
            return false;
        }
        for(int i=0;i<s.length()-1;i++) {
        	if (isoperator(s.charAt(i)) && isoperator(s.charAt(i+1))||((isoperator(s.charAt(i)))&& (s.charAt(i+1)==')'))||((s.charAt(i)=='(')&&(isoperator(s.charAt(i+1)))) || ((s.charAt(i))==')')&& (s.charAt(i+1)=='(')) {
        		return false;
        	}
        	
        }
        for (char c:s.toCharArray()){
        	
            if(c == '('){
                b++;

            }
            else if (c==')'){
                b--;
            }
            else if (b<0){
                return false;
            }
            else if(!(Character.isLetterOrDigit(c)|| isoperator(c))) {
            	return false;
            }

           
            
            
        
        }
        return b==0;
    }

   
	public static boolean isoperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }

    public static String in_to_pre(String s) {
        
        Stack<String> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (char i : s.toCharArray()) {
            
            if (i == '(') {
                operators.push(i);
            } else if (i == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    char op = operators.pop();
                    String right = operands.pop();
                    String left = operands.pop();
                    String new_exp = op + left + right;
                    operands.push(new_exp);
                }
                operators.pop();
            } else if (isoperator(i)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(i)) {
                    char op = operators.pop();
                    String right = operands.pop();
                    String left = operands.pop();
                    String new_exp = op + left + right;
                    operands.push(new_exp);
                }
                operators.push(i);
            } else if (i != ' ') {
                operands.push(String.valueOf(i));
            }
            else if ((i==s.length()-1) && isoperator(i)){
                return "invalid expression..";
            }
        }
        
        while (!operators.isEmpty()) {
            char op = operators.pop();
            String right = operands.pop();
            String left = operands.pop();
            String new_exp = op + left + right;
            operands.push(new_exp);

        }
        return operands.peek();
        
    
    }

    }

