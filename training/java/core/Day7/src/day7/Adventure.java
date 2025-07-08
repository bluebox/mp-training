package day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Adventure {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		Map<String,Places> world=new HashMap<>();
		
		Places Road=new Places("road","Long highway Roads");
		Places WellHouse=new Places("wellHouse","Haunting house with a deep well");
		Places Valley=new Places("valley","long narrow valley");
		Places Stream=new Places("stream","Never ending river streams");
		Places Forest=new Places("forest","Dense forests with big wild trees");
		Places Hill=new Places("hill","big steep hills with deadly temperatures");
		Places Lake=new Places("lake","Big shallow lake with fish");
		
		Road.addDirections("N","Forest");
		Road.addDirections("S","Valley");
		Road.addDirections("W","Hill");
		Road.addDirections("E","WellHouse");
		
		WellHouse.addDirections("N","Lake");
		WellHouse.addDirections("S","Stream");
		
		Valley.addDirections("N", "Road");
		
		Stream.addDirections("N", "WellHouse");
		Stream.addDirections("E", "Valley");
		
		Forest.addDirections("E", "Lake");
		Forest.addDirections("S", "Road");
		
		Hill.addDirections("N", "Forest");
		Hill.addDirections("E", "Road");
		
		Lake.addDirections("S", "WellHouse");
		Lake.addDirections("W", "Forest");
		
		
		world.put("Road", Road);
		world.put("WellHouse", WellHouse);
		world.put("Valley", Valley);
		world.put("Stream", Stream);
		world.put("Forest", Forest);
		world.put("Hill", Hill);
		world.put("Lake", Lake);
		String current="Road";
//		System.out.println(world.get(current));
		Places p1=world.get(current);
		System.out.println("Current place : "+p1.toString());
		System.out.println("-".repeat(30));
		System.out.println("showing all exits : ");
		System.out.println(p1.getDirections());
		while(true) {
			System.out.println("-".repeat(30));
			System.out.println("enter the direction you want to move (N/S/E/W) and Q to quit: ");
			String s=sc.nextLine();
			s=s.toUpperCase();
			if(p1.directions.containsKey(s.toUpperCase())){
				p1=world.get(p1.directions.get(s));
				System.out.println("you moved to "+p1.getName()+" and its : "+p1.getDescription());
				System.out.println("its all exits : ");
				System.out.println(p1.getDirections());
				System.out.println("Enter where you want to move");}
			else if(s.equals("Q")) {
				System.out.println("exiting bye");
				break;
			}
			else{
				System.out.println("Invalid input");
				continue;
			}
		}
	}
	
}
