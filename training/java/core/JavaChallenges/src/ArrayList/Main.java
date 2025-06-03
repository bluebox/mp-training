package ArrayList;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		
		ArrayList<Album> al = new ArrayList<>();
		Album a = new Album("robo","uday" );
		a.addsongs("a",1);
		a.addsongs("b",2);
		a.addsongs("c",3);
		
		al.add(a);
		
		System.out.println(al.toString());

	}

}