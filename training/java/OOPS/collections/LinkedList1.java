package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedList1 {

	public static void main(String[] args) {
		
		Town t1= new Town("sydney",0);
		Town t2= new Town("Adelaide",1374);
		Town t3= new Town("Alice Spring",2771);
		Town t4= new Town("Brisbane",917);
		Town t5= new Town("Darwin",3972);
		Town t6= new Town("Melbourne",877);
		Town t7= new Town("perth",3923);
		List<Town> list= new LinkedList<Town>(List.of(t1,t2,t3,t4,t5,t6,t7))	;
		Collections.sort(list,new Comparator<Town>()
				{
					public int compare(Town o1,Town o2)
					{
						return 	Integer.compare(o1.getDistance(),o2.getDistance());
					}
				}
				);
		ListIterator<Town> itr= list.listIterator();
//		while(itr.hasNext())
//		{
//			System.out.println(itr.next().getTownName());
//		}
		do {
		System.out.println("select options:\n"
				+ "1.(F)ordard\n2.(B)ackward\n3.(p)rint\n4.(Q)uit");
		Scanner sc= new Scanner(System.in);
		String ch= sc.next();
		
		switch(ch)
		{
		case "f" -> {if(itr.hasNext())
				{
					 itr.next();
				}}
		case "b" ->
		{
			if(itr.hasPrevious())
			{
				itr.previous();			}
		}
		case "q"->
		{
			return ;
		}
		case "p"->{
			if(itr.hasNext())
			{System.out.println(itr.next().getTownName());
			itr.previous();
			}
			else
			{
				System.out.println("no more towns to visit go back");
			}
			}
			default->{
				System.out.println("ntg");
			}
		};
		}while(true);
	}

}
class Town
{
	private String townName;
	private int distance;
	public Town(String townName, int distance) {
		this.townName = townName;
		this.distance = distance;
	}
	public String getTownName() {
		return townName;
	}
	public int getDistance() {
		return distance;
	}
	
		
	
}
