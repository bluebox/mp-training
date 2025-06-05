import java.math.BigDecimal;

public class BigDecimalExample {
	
	public static void main(String[] args) {
	    BigDecimal a=new BigDecimal("1000000000.0000001");
	    System.out.println(a.scale());
	    System.out.println(a.precision());
	    BigDecimal b=BigDecimal.valueOf(10.156789);
	    System.out.println(b);
	    System.out.println(b.scale());
	    System.out.println(b.precision());
	    b=b.setScale(2,0);
	    System.out.println(b);
	}
}
