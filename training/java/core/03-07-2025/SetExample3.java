import java.util.*;
public class SetExample3 {
	
	
	ArrayList<Integer> list1=new ArrayList<>();
{
		for(int i=1;i<5;i++)
		{
			list1.add(i);
		}
}

ArrayList<Integer> list2=new ArrayList<>();
{
	for(int i=3;i<8;i++)
	{
		list2.add(i);
	}
}

public void getUnion()
{
	System.out.println("Union elements are: ");
	for(Integer l1:list1)
	{
		System.out.println(l1);
	}
	for(Integer l2:list2)
	{
		if(!list1.contains(l2))
		{
			System.out.println(l2);
		}
	}
}

public void getIntersection()
{
	System.out.println("Intersection elements are: ");
	for(Integer l1:list1)
	{
		if(list2.contains(l1))
		{
			System.out.println(l1);
		}
	}
}

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SetExample3 exp=new SetExample3();
		exp.getUnion();
		
		exp.getIntersection();

	}

}
