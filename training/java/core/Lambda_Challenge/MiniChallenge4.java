package Lambda_Challenge;

import java.util.function.UnaryOperator;

public class MiniChallenge4 {
    public static String processString(int num , UnaryOperator<String> Operator ){
        String st = String.valueOf(num);
        return Operator.apply(st);
    }
    public static void main(String[] args) {
        int num= 12345678;
       String result = processString(num, str -> {
            
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < str.length(); i += 2) {
                sb.append(str.charAt(i));
            }
            return sb.toString();
        });

        System.out.println(result); 
    }
}
/* 
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        String input = "hello";

        // Call the method, passing a lambda as UnaryOperator
        String result = applyOperator(input, str -> str.toUpperCase());

        System.out.println(result);
    }

    // Method accepting UnaryOperator as parameter
    public static String applyOperator(String s, UnaryOperator<String> operator) {
        return operator.apply(s);
    }
}

*/