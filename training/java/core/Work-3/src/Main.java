
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumber n1=new ComplexNumber(1.0,1.0);
		ComplexNumber n2=new ComplexNumber(2.5,-1.5);
		n1.add(n2);
		System.out.println(n1.getReal()+" "+n1.getImagi());
		n1.add(1.0,1.0);
		System.out.println(n1.getReal()+" "+n1.getImagi());
		
		Floor f1=new Floor(2.75,4.0);
        Carpet c1=new Carpet(3.5);
        Calculator c=new Calculator(f1,c1);
        System.out.println("total cost : "+c.getTotalCost());
	}

}
