
import java.util.Scanner;
import java.util.Stack;

public class Htmltag
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		Stack<String> st=new Stack<String>();
		boolean t=true;
		int i=0; 
		String c="";
		while(i<str.length()){
		    System.out.println("i"+i);
		    if(str.charAt(i)=='<'){
		        boolean k=false;
		        while(str.charAt(i)!='>'){
		            if(str.charAt(i)=='/'){
		                k=true;
		            }
		            else
		            c+=str.charAt(i);
		            i++;
		        }
		        if(k==true){
		            c+=">";
		                if(st.size()>0 && st.peek().equals(c)){
		                    st.pop();
		                    c="";
		                    
		                    
		  
		                }
		                else{
		                    t=false;
		                    
		                }
		        }
		        if(!c.equals("")){
		            c+=">";
		            st.push(c);
		            c="";
		        }
		    }
		    else{
		        if (str.charAt(i)=='['||str.charAt(i)=='{'||str.charAt(i)=='(')
		        st.push(String.valueOf(str.charAt(i)));
		        else{
		            if(st.size()>0 &&((str.charAt(i)==']'&&st.peek().equals("["))||(str.charAt(i)=='}'&&st.peek().equals("{"))||(str.charAt(i)==')'&&st.peek().equals("(")))){
		                st.pop();
		            }
		            else{
		                System.out.println("hi");
		                t=false;
		            }
		        }
		    }
		    i++;
		    
		}
		System.out.println(st.size());
		if(st.size()!=0)
		t=false;
		if(t==false)
		System.out.println("invalid");
		else{
		    System.out.println("valid");
		}
	}
}
