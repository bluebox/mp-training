import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Problem {

    public static void main(String[] args) {
        Stack<String> st = new Stack<>();
        Scanner sc=new Scanner(System.in);
        boolean b=false;
        String s=sc.next();
        for(char ch:s.toCharArray()){
            if((ch<='Z' && ch>='A') || (ch<='z' && ch>='a' ) ||(ch>='0'&& ch<='9')){
                continue;
            }
            else if((ch=='(' )){
                
                if(s.indexOf(ch)==s.length()-1||!((s.charAt(s.indexOf(ch)+1)<='Z' && s.charAt(s.indexOf(ch)+1)>='A') || (s.charAt(s.indexOf(ch)+1)<='z' && s.charAt(s.indexOf(ch)+1)>='a' ) ||(s.charAt(s.indexOf(ch)+1)>='0'&& s.charAt(s.indexOf(ch)+1)<='9'))){
                    b=true;
                    break;
                }
                
                if((s.indexOf(ch)==0||s.charAt(s.indexOf(ch)-1)<='Z' && s.charAt(s.indexOf(ch)-1)>='A') || (s.charAt(s.indexOf(ch)-1)<='z' && s.charAt(s.indexOf(ch)-1)>='a' ) ||(s.charAt(s.indexOf(ch)-1)>='0'&& s.charAt(s.indexOf(ch)-1)<='9')){
                    continue;
                }
            }
            else if(ch==')'){
                if(!((s.charAt(s.indexOf(ch)-1)<='Z' && s.charAt(s.indexOf(ch)-1)>='A') || (s.charAt(s.indexOf(ch)-1)<='z' && s.charAt(s.indexOf(ch)-1)>='a' ) ||(s.charAt(s.indexOf(ch)-1)>='0'&& s.charAt(s.indexOf(ch)-1)<='9'))){
                    System.out.println("hello");
                    b=true;
                    break;
                }
                if(s.indexOf(ch)==0||s.indexOf(ch)==s.length()-1||(s.charAt(s.indexOf(ch)+1)<='Z' && s.charAt(s.indexOf(ch)+1)>='A') || (s.charAt(s.indexOf(ch)+1)<='z' && s.charAt(s.indexOf(ch)+1)>='a' ) ||(s.charAt(s.indexOf(ch)+1)>='0'&& s.charAt(s.indexOf(ch)+1)<='9')){
                    continue;
                }
            }
            
            else{
                if(s.indexOf(ch)==0||s.indexOf(ch)==s.length()-1  ||s.charAt(s.indexOf(ch)-1)=='+'||s.charAt(s.indexOf(ch)-1)=='+'||s.charAt(s.indexOf(ch)-1)=='-'||s.charAt(s.indexOf(ch)-1)=='*'||s.charAt(s.indexOf(ch)-1)=='/'){
                    System.out.println("invalid expression");
                    b=true;
                    break;
                }
            }
        }
        String[] exp=s.split("");
        System.out.println(Arrays.toString(exp));
        exp=reverse(exp);
        System.out.println(Arrays.toString(exp));
        StringBuilder prefix=new StringBuilder("");
        int brackets=0;
        System.out.println(b);
        
        if(b==false)
        {for(String str:exp){
            if(str.equals(")" )){
                
                brackets+=1;
                st.push(str);
                
            } 
            else if(str.equals("*")||str.equals("/")){


                st.push(str);
            }
            else if(str.equals("+")||str.equals("-")){
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
        } 
        while (st.size()>0) {
            if(!st.peek().equals(")"))
            prefix.append(st.pop());
            else
            break;
        
        } 
       

}
else{
    System.out.println("invalid expression");
}
 System.out.println(prefix);
        if(st.size()==0 && brackets==0)
        System.out.println(prefix.reverse());
        else
        System.out.println("invalid expression");
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
