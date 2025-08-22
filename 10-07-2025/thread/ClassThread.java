package ThreadBasic;

import java.lang.Thread;
public class ClassThread extends Thread{
	
	public void run() {
		for(int i=2;i<=10;i+=2) {
			System.out.println(i+" ");
			
		}
	}

}
