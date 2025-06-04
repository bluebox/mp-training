package bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
	public static void main(String[] args) {
		// It is immutable
		BigDecimal bg = new BigDecimal("23435.564");
		System.out.println(bg.unscaledValue() + " " + bg.precision() + " " + bg.scale());
		BigDecimal a = new BigDecimal("10");
		BigDecimal b = new BigDecimal("3");
		
		
		BigDecimal ans = a.divide(b, new MathContext(10, RoundingMode.HALF_UP));
		System.out.println(a.divide(b,MathContext.DECIMAL32));
		
		System.out.println(ans);
		System.out.println(a);
		System.out.println(b);

		// Don't use like this
		BigDecimal dob = new BigDecimal(1223423.46556768456);
		System.out.println(dob.unscaledValue() + " " + dob.scale() + " " + dob.precision());

		// this is ok
		BigDecimal dob1 = BigDecimal.valueOf(1223423.46556768456);
		System.out.println(dob1.unscaledValue() + " " + dob1.scale() + " " + dob1.precision());

		BigDecimal num = new BigDecimal("97");
		System.out.println(num.remainder(b));
		System.out.println(num.multiply(b));
		System.out.println(num.add(b));

		BigDecimal num1 = new BigDecimal("1.2345e2");
		System.out.println(num1);
		//System.out.println(new MathContext("23")); wrong
		System.out.println(dob1.round(MathContext.DECIMAL128));
		

	}

}
