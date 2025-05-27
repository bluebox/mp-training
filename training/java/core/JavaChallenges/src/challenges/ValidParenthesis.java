package challenges;

import java.util.*;

public class ValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the String:");
		String s=sc.next();
//		String r=s.replaceAll("<h1>","<");
//		String v=r.replaceAll("</h1>",">");
		boolean res=isValid(s);
		System.out.println(res);
	}
	private static boolean isValid(String v)
	{
		Stack<Character> st=new Stack<>();
		for(int i=0;i<v.length();i++)
		{
			char ch=v.charAt(i);
			if(ch=='/' || ch=='h' || ch=='>' || ch=='1')
			{
				continue;
			}
			if(ch=='<' && v.charAt(i+1)=='/')
			{
				ch='>';
			}
			
			if(st.size()==0 || ch=='(' || ch=='{' || ch=='[' || ch=='<')
			{
				st.push(ch);
			}
			else
			{
				if(ch==')')
				{
					if(st.peek()=='(')
					{
						st.pop();
					}
					else
					{
						return false;
					}
				}
				else if(ch=='}')
				{
					if(st.peek()=='{')
					{
						st.pop();
					}
					else
					{
						return false;
					}
				}
				if(ch==']')
				{
					if(st.peek()=='[')
					{
						st.pop();
					}
					else
					{
						return false;
					}
				}
				if(ch=='>')
				{
					if(st.peek()=='<')
					{
						st.pop();
					}
					else
					{
						return false;
					}
				}
				
			}
		}
		return st.size()==0;
		
	}

}
