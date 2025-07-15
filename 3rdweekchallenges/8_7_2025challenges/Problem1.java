
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Problem1 {

    public static void main(String[] args) {
        Stack<String> st = new Stack<>();
        Scanner sc=new Scanner(System.in);
        boolean b=false;
        String s=sc.next();
        if(s.charAt(0)=='+'||s.charAt(0)=='-'||s.charAt(0)=='*'||s.charAt(0)=='/'){
            System.out.println("invalid expression");
            b=true;
        }
        if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='-'||s.charAt(s.length()-1)=='*'||s.charAt(s.length()-1)=='/'){
            System.out.println("invalid expression");
            b=true;
        }
        
        
        String[] exp=s.split("");
        System.out.println(Arrays.toString(exp));
        exp=reverse(exp);
        System.out.println(Arrays.toString(exp));
        StringBuilder prefix=new StringBuilder("");
        int brackets=0;
        int index=0;
        if(!b)
       {for(String str:exp){
            if(str.equals(")" )){
                brackets+=1;
                st.push(str);
            } 
            else if(str.equals("*")||str.equals("/")){
                if(index==0||index==s.length()||s.charAt(index-1)=='+' || s.charAt(index-1)=='*'||s.charAt(index-1)=='/'||s.charAt(index-1)=='-'){
                    System.out.println("invalid expression hi");
                    break;
                }
                st.push(str);
            }
            else if(str.equals("+")||str.equals("-")){
                if(s.charAt(index-1)=='+' || s.charAt(index-1)=='*'||s.charAt(index-1)=='/'||s.charAt(index-1)=='-'){
                    System.out.println("invalid expression");
                    break;
                }
                while (st.size()>0 && (st.peek().equals("*")||st.peek().equals("/"))) {
                    prefix.append(st.pop());
                    
                }
                st.push(str);
            }
            else if(str.equals("(")){
                brackets-=1;
                while (st.size()>0 && !st.peek().equals(")")){
                    
                    prefix.append(st.pop());
                    System.out.println(prefix);
                    
                }
                System.out.println(st.toString());
                if(st.size()>0 && st.peek().equals(")"))
                st.pop();
            }
            else{
                prefix.append(str);
            }
            index++;
        }
        
        while (st.size()>0) {
            if(!st.peek().equals(")"))
            prefix.append(st.pop());
            else
            break;
        
        }
        
        if(st.size()==0 && brackets==0)
        System.out.println(prefix.reverse());
        else
        {System.out.println("st"+st.size());
        System.out.println("invalid expression");}


    
        


}
    }
    public static String[] reverse(String[] arr){
        int i=0;
        int j=arr.length-1;
        while(i<j){
            String temp;
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
        return arr;


    }
    



}
