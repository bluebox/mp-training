package june26_methods;
import java.util.Scanner;
public class SimpleCalculator {
	
	double firstnumber;
	double secondnumber;
	
	public double getfirstnumber() {
		return firstnumber;
	}
	
	public double getsecondnumber() {
		return secondnumber;
	}
	
	public double getadditionresult() {
		return (firstnumber+secondnumber);
	}

	public double getsubtractionresult() {
		return (firstnumber-secondnumber);
	}

	public double getmultiplicationresult() {
		return (firstnumber*secondnumber);
	}

	public double getdivisionresult() {
		return (firstnumber/secondnumber);
	}
	
	public void setfirstnumber(double fnum) {
		firstnumber=fnum;
	}
	
	public void setsecondnumber(double snum) {
		secondnumber=snum;
	}
	

	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter two numbers");
		double fnum=sc.nextDouble();
		double snum=sc.nextDouble();
		SimpleCalculator c=new SimpleCalculator();
		c.setfirstnumber(fnum);
		c.setsecondnumber(snum);
		System.out.println("First number :"+c.getfirstnumber());
		System.out.println("Second number :"+c.getsecondnumber());
		System.out.println("Addition Result :"+c.getadditionresult());
		System.out.println("Subtraction Result :"+c.getsubtractionresult());
		System.out.println("Multiplication Result :"+c.getmultiplicationresult());
		System.out.println("Division Rsult :"+c.getdivisionresult());
		sc.close();
		
		
	}

}
