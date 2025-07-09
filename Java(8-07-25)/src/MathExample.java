import java.util.Random;

public class MathExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int maxMinusFive=Integer.MAX_VALUE-5;
//		for(int j=0,id=maxMinusFive;j<10;id=Math.incrementExact(id),j++) {
//			System.out.println(id);
//		}
		System.out.println(Math.abs(Integer.MIN_VALUE));
	    System.out.println((long)(Integer.MIN_VALUE));
	    //System.out.println(Math.absExact(Integer.MIN_VALUE));
	    System.out.println("Rounding off to "+Math.round(10.8));
	    System.out.println("Rounding off to "+Math.round(10.2));
	    System.out.println("floor value is "+Math.floor(10.2));
	    System.out.println("ciel value is "+Math.ceil(10.2));
	    System.out.println("Square root of a given number "+Math.sqrt(100));
	    System.out.println("Power of two numbers "+Math.pow(2, 3));
	    System.out.println("printing random numbers");
	    for(int i=0;i<10;i++)
	    {
	    	System.out.println(Math.random());
	    }
	    System.out.println("printing random numbers using Random class");
	    Random r=new Random();
	    r.ints(5)
	    .forEach(System.out::println);
	    r.ints(5,10,20)
	    .forEach(System.out::println);
	    r.ints(7,15)
	    .limit(5)
	    .forEach(System.out::println);
	}
}


