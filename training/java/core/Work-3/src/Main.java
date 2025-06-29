
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumber n1=new ComplexNumber(1.0,1.0);
		ComplexNumber n2=new ComplexNumber(2.5,-1.5);
		n1.add(n2);
		System.out.println(n1.getReal()+" "+n1.getImagi());
		n1.add(1.0,1.0);
		System.out.println(n1.getReal()+" "+n1.getImagi());
		System.out.println("-".repeat(15));
		
		
		Floor f1=new Floor(2.75,4.0);
        Carpet c1=new Carpet(3.5);
        Calculator c=new Calculator(f1,c1);
        System.out.println("total cost : "+c.getTotalCost());
        System.out.println("-".repeat(15));
        
        Circle circle=new Circle(3.75);
        System.out.println("circle radius : "+circle.getRadius()+" its area : "+circle.getArea());
        Cylinder cylinder=new Cylinder(5.55,7.25);
        System.out.println("cylinder radius : "+cylinder.getRadius()+" height : "+cylinder.getHeight()+" volume : "+cylinder.getVolume());
        System.out.println("-".repeat(15));
        
        
        Rectangle rec=new Rectangle(5,10);
        System.out.println("length : "+rec.getLength()+" width : "+rec.getWidth()+" Area : "+rec.getArea());
        Cuboid cubi=new Cuboid(5,10,5);
        System.out.println("height : "+cubi.getHeight()+" length : "+cubi.getLength()+"width : "+cubi.getWidth()+" Volume : "+cubi.getVolume());
        System.out.println("-".repeat(15));
        
        
        SmartKitchen smart=new SmartKitchen();
        smart.setKitchenState(true, true, false);
        smart.doKitchenWork();
        System.out.println("-".repeat(15));
        
        Printer printer=new Printer(50,true);
        System.out.println(printer.addToner(50));
        System.out.println("Intial page count : "+printer.getPagesPrinted());
        int pagesPrinted=printer.printPages(4);
        System.out.println("Pages printed : "+pagesPrinted+" total print counter : "+printer.getPagesPrinted());
        pagesPrinted=printer.printPages(2);
        System.out.println("Pages printed : "+pagesPrinted+" total print counter : "+printer.getPagesPrinted());


	}

}
