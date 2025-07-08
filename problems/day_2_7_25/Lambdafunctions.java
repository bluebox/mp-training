package day_2_7_25;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;



public class Lambdafunctions {

	public static void main(String[] args) {
		
		// miniChallenge1
		// anonymous inner class using functional interface
//		Consumer<String> consumer=new Consumer<String>(){
//
//			@Override
//			public void accept(String t) {
//				// TODO Auto-generated method stub
//				String [] parts=t.split(t);
//				for(String part:parts) {
//					System.out.println(part);
//				}
//			}
//			
//		};
//		
		
		// using lambda function
		Consumer<String> printwords=s->{
					String [] parts=s.split(" ");
					for(String part:parts) {
						System.out.println(part);
					}
				};
				
				printwords.accept("Prabhas is a good boy");
		
		
		
		
      // minichallenge2
	      UnaryOperator<String>  string=(s)->{
			StringBuilder retval=new StringBuilder();
			for(int i=0;i<s.length();i++) {
			if(i%2==1) {
				retval.append(s.charAt(i));
			}
		}
			return retval.toString();
		};
		
		String st=string.apply("PrabhasMutawar");
		System.out.println(st);
		
		
		//minichallenge3
		UnaryOperator<String>  string1=(s)->{
			StringBuilder retval=new StringBuilder();
			for(int i=0;i<s.length();i++) {
			if(i%2==1) {
				retval.append(s.charAt(i));
			}
		}
			return retval.toString();
		};
		
		String st1=string.apply("1234567890");
		System.out.println(st1);
		
		//minichallenge4
		UnaryOperator<String>  string2=(s)->{
			StringBuilder retval=new StringBuilder();
			for(int i=0;i<s.length();i++) {
			if(i%2==1) {
				retval.append(s.charAt(i));
			}
		}
			return retval.toString();
		};
		String result=everySecondCharacter(string2,"1234567890");
		System.out.println(result);
		
		// minichallenge5
		String result1=everySecondCharacter(string2,"1234567890");
		System.out.println(result1);
		
		//minichallenge6
		Supplier<String> Supplier = () -> "I Love Java";
        String ILoveJava=Supplier.get();
        System.out.println(ILoveJava);
        
        //minichallenge7
        String supplierresult=getSupplier(Supplier);
        System.out.println(supplierresult);
		
}
	
	public static String getSupplier(Supplier<String> Supplier) {
		return Supplier.get();
	}
	
	public static String everySecondCharacter(UnaryOperator<String> operator,String value ) {
		 return operator.apply(value);
	}
}


		
		
		
	


