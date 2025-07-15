import java.util.*;

public class InfixToPrefix {


     public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }


     public static int precedence(char c) {
        switch (c) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

  
    public static String reverseAndReplace(String expr) {
        StringBuilder result = new StringBuilder();
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(') result.append(')');
            else if (c == ')') result.append('(');
            else result.append(c);
        }
        return result.toString();
    }

    // public static boolean isValid(String expr) {
    //     int balance = 0;
    //     for (char c : expr.toCharArray()) {
    //         if (c == '(') balance++;
    //         else if (c == ')') balance--;
    //         if (balance < 0) return false;
    //     }
    //     return balance == 0;
    // }

    
    public static String infixToPrefix(String infix) {
        //if (!isValid(infix)) return "Invalid Expression";

        infix = reverseAndReplace(infix);
        Stack<String> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i+1);

            if (Character.isLetterOrDigit(c)) {
                operands.push(c + "");
            }
            else if (c == '(') {
                operators.push(c);
            }
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    operands.push(op + op2 + op1);
                }
                if (!operators.isEmpty() && operators.peek() == '(')
                    operators.pop();
                else
                    return "Invalid Expression"; 
            }
            else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) < precedence(operators.peek())) {
                    String op1 = operands.pop();
                    String op2 = operands.pop();
                    char op = operators.pop();
                    operands.push(op + op2 + op1);
                }
                operators.push(c);
            }
            else {
                return "Invalid Expression"; 
            }
        }

        while (!operators.isEmpty()) {
            // if (operands.size() < 2) return "Invalid Expression";
            String op1 = operands.pop();
            String op2 = operands.pop();
            char op = operators.pop();
            operands.push(op + op2 + op1);
        }

        //return operands.size() == 1 ? operands.pop() : "Invalid Expression";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine().replaceAll("\\s+", ""); 

        String result = infixToPrefix(infix);
        System.out.println("Prefix Expression: " + result);
    }
}