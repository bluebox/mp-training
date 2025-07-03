package Day6_02_07_miniChallanges;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;


public class Main {
	public static void main(String args[]) {
		Consumer<String> printer=s->System.out.println(s);
		//challange1
		Consumer<String> printer1=s->{
			String[] arr=s.split(" ");
			for(String a:arr) {
				System.out.println(a);}
			};

		printer.accept("Hello");
		printer1.accept("kjcn a.kfjcn akjcend .kijdc");
		//challange2
		Consumer<String> printer2=s->{
			StringBuilder ch2_return=new StringBuilder();
			for(int i=1;i<ch2_return.length();i+=2) {
				ch2_return.append(s.charAt(i));
			}  
			System.out.println(ch2_return);
			};
		printer2.accept("65784512324651");
			
		//challange3
		UnaryOperator<String> second=s->{
			StringBuilder SBReturn=new StringBuilder();
			for(int i=1;i<s.length();i+=2) {
				SBReturn.append(s.charAt(i));
			}
			return SBReturn.toString();
		};
		System.out.println(second.apply("687451"));
		
		Predicate<Integer> second4=s->{
			
			return !(s%2==0);
		};
		System.out.println(second.apply("687451"));
		
		//challenge 4
		everySecondCharacter(second4,"98465129465");
		//challenge6
		Supplier<String> ch5=()->{
			String iLoveJava="I Love Java";
			return iLoveJava;
			};
		System.out.println(ch5.get());
		//challenge7
		Supplier<String> ch7=()->{
			String iLoveJava="I Love Java";
			return iLoveJava;
			};
			String retur=ch7.get();
			System.out.println(retur);
		
	
	}
	public static void everySecondCharacter(Predicate<Integer> second,String str) {
		StringBuilder outputString = new StringBuilder(); 
		for(int i=0; i<str.length(); i++) {
			if(second.test(i)) {
				outputString.append(str.charAt(i));
			}
		}
		System.out.println(outputString.toString());
	}
}
