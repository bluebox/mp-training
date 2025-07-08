package Day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Day6.SetChallenge.Priority;
import Day6.SetChallenge.Status;

public class Main {
	public static void main(String[] args) {
		List<SetChallenge> tasks=new ArrayList<>(); 
				tasks.add(new SetChallenge("Raji","Windmill" ,"generating electricity throug wind", Status.ASSIGNED, Priority.HIGH));
				tasks.add(new SetChallenge("Buji","Hydro" ,"generating electricity throug water", Status.NOT_ASSIGNED, Priority.HIGH));
				tasks.add(new SetChallenge("Suri","Earth" ,"generating electricity throug Earth temp", Status.ASSIGNED, Priority.LOW));
		Collections.sort(tasks , SetChallenge.checker);
		tasks.forEach(s->System.out.println(s));
		
		System.out.println();
	}
}

