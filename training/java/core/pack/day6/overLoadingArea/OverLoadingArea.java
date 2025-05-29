package day6.overLoadingArea;
import java.util.*;

public class OverLoadingArea {
	public static void main(String args[]) {
		
	try{
		FindArea f=new FindArea();
		System.out.println("The area of the rectangle is "+f.area(12.5,13.6));
		System.out.println("The area of the square is "+f.area(12.5));
	
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
