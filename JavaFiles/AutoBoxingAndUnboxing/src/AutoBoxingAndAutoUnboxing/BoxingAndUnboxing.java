package AutoBoxingAndAutoUnboxing;

import java.util.ArrayList;

public class BoxingAndUnboxing {
	
	public static void main(String[] args) {
	
	Integer in= new Integer(10);
	int inn=10;
	
	System.out.println(in instanceof Integer);
	System.out.println(in.equals(inn));
	
	ArrayList<Integer> arr=new ArrayList<>();
	
	for(int i=0;i<10;i++) {
		arr.add(i);
	}
	
	for(Integer j : arr) {
		System.out.println(Integer.valueOf(j));
	}
	

}
}