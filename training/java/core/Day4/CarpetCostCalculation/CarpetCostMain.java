package CarpetCostCalculation;

public class CarpetCostMain {
public static void main(String []args)
{
	Carpet c1=new Carpet(7);
	Floor f1=new Floor(3,4);
	Calculator cal1=new Calculator(f1,c1);
	System.out.println(cal1.gettotalcost());
	
}
}