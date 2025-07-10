package Threads;

import java.util.ArrayList;
import java.util.List;

public class EvenOrOddCustomThread extends Thread {
	
	public void run() {
		List<Integer> odd = new ArrayList<>();
		
		int o=1;
		
		while(odd.size()!=5){
			try {
				Thread.sleep(500);			
			}catch(InterruptedException er) {
				er.setStackTrace(null);
			}
			System.out.println("Running the Odd Thread");
			if(o%2!=0) {
				odd.add(o);
				System.out.println("Odd number added to odd list");
			}
			o++;
			System.out.println("Odd List : "+odd);
		}
	}

}
