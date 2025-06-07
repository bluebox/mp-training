package day11.Interface;

import java.util.ArrayList;
import java.util.List;

public class Main {
 public static void main(String args[]) {
	 
	 Player p=new Player(5,6,"Raghu");
	 System.out.println(p);
	 Monster m=new Monster("mangoose",11,18);
	 System.out.println(m);
	 
	 List<String>lis1=p.write();
	 p.read(lis1);
	 
	 List<String>lis=m.write();
	 m.read(lis);
	 
	 
	 
 }
}
