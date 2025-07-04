import java.util.*;
import java.util.Arrays;

public class MethodReference1 {
	
	//Reference to a static method
//	public static void print(String name)
//	{
//		System.out.println("Hello! My  name is "+name);
//	}
	
	//reference to a non-static method
	public void print(String name)
	{
		System.out.println("Hello ! my name is "+name);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list=Arrays.asList("Tarun","ravi","jagadeesh","balu");
		
//		 list.forEach(MethodReference1::print);
		
		MethodReference1 met=new MethodReference1();
		list.forEach(met::print);
	}
	

}
