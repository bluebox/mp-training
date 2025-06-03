package dev;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<User> users = new ArrayList<>();
		
		User u1 = new User("Abhinav",20,1,true);User u2 = new User("Meghana",22,2,true);User u3 = new User("Devansh",21,3,true);User u4 = new User("Dheeraj",21,4,true);User u5 = new User("Saiprasad",22,5,true);User u6 = new User("sharan",23,6,true);User u7 = new User("Anjiteja",35,7,true);User u8 = new User("RohitKumar",18,8,true);
		User u9 = new User("Rohitreddy",18,9,true);User u10 = new User("Krishan",18,10,true);User u11 = new User("Abhiram",19,11,true);User u12 = new User("rahul",45,12,false);User u13 = new User("sam",20,13,false);User u14 = new User("kamala",38,14,true);User u15 = new User("murnal",19,15,false);User u16 = new User("kurnal",46,16,false);
		User u17 = new User("srinivas",28,17,true);User u18 = new User("Meghana bai ",22,18,false);
		
		users.add(u1);users.add(u2);users.add(u3);users.add(u4);users.add(u5);users.add(u6);users.add(u7);users.add(u8);users.add(u9);
		users.add(u10);users.add(u12);users.add(u13);users.add(u14);users.add(u15);users.add(u16);users.add(u17);users.add(u18);users.add(u11);

		var tempStream = users.stream().limit(5);
		
		tempStream.forEach(d -> System.out.println(d));
		
		var tempStream1 = users.stream()
				.filter(d ->!d.isActiveUser())
				.limit(2);
		
		tempStream1.forEach(d-> System.out.println(d));
		
		var stream1 = Stream.iterate(0,i->i+1)
				.filter(Math::isEven)
				.limit(100);
		
		stream1.forEach(d->{System.out.println(d);
							System.out.println("----");
							});
		
		
				
	}

}
