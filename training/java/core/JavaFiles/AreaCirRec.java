
public class AreaCirRec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AreaCir(5);
		AreaRec(2,3);
		AreaCir(7);
		AreaRec(20,30);
		AreaCir(9);
		AreaRec(2,9);

	}
	
	public static void AreaCir(int r) {
		System.out.println("Area of Circle :"+3.14*r*r);
	}
	
	public static void AreaRec(int l,int b) {
		System.out.println("Area of Rectangle :"+(l*b));
	}
	

}
