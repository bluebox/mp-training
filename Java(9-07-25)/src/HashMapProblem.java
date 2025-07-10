import java.util.ArrayList;
import java.util.*;
public class HashMapProblem {

	private static final String HashMap = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer>even=new ArrayList<>();
		ArrayList<Integer>odd=new ArrayList<>();
		HashMap<String,ArrayList>hm=new HashMap<>();
		for(int i=1;i<=200;i++)
		{
			if(i%2==0)
			{
				even.add(i);
			}
			else
			{
				odd.add(i);
			}	
		}
		hm.put("even", even);
		hm.put("odd", odd);
		System.out.println("even numbers are "+hm.get("even"));
		System.out.println("even numbers are "+hm.get("odd"));
		
	}

}
