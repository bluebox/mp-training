import java.util.*;
public class StackChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> st=new Stack<>();
		st.push("Lion");
		st.push("Elephant");
		st.push("tiger");
		st.push("fox");
		System.out.println("The stack contains :"+st);
		st.pop();
		System.out.println("The stack contains :"+st);
		System.out.println("Peek element is "+st.peek());
		System.out.println(st.search("Lion"));
		System.out.println(st.empty());
	}

}
