package training_java.May26;

import java.util.Stack;

public class ValidParan{
    static boolean isBalanced(String s) {
        Stack<Character> st = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                st.push(s.charAt(i));
            }
           
            else if(s.charAt(i)=='<'){
                if(s.charAt(i+1)=='h'){
                    st.push(s.charAt(i));
                    i=i+4;
                    continue;
                }
                else{
                    if(!st.empty()&&(st.peek()=='<')){
                        st.pop();
                        i=i+5;
                        continue;
                    }
                    else{
                        return false;
                    }
                }
            }
           
            else {
                if (!st.empty() &&
                    ((st.peek() == '(' && s.charAt(i) == ')') ||
                     (st.peek() == '{' && s.charAt(i) == '}') ||
                     (st.peek() == '[' && s.charAt(i) == ']'))) {
                    st.pop();
                }
                else{
                    return false;
                }
            }
           
            i++;
        }
        return st.empty();
    }

    public static void main(String[] args) {
        String s = "<h1><h1>[]</h1>";
        System.out.println(isBalanced(s));
    }
}


