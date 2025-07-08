import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;


public class MiniChallange {
	public static void main(String args[]) {
		Consumer<String> CombinedOut=s->System.out.println(s);

		
		
		
		Consumer<String> SplitOut=s->{
			String[] arr=s.split(" ");
			for(String a:arr) {
				System.out.println(a);}
			};

		CombinedOut.accept("Hello All Welcome to Hyderabad");
		SplitOut.accept("Hello All Welcome To Hyderabad");

		
		
		
		Consumer<String> EverySecond=s->{
			StringBuilder ch2_return=new StringBuilder();
			for(int i=1;i<ch2_return.length();i+=2) {
				ch2_return.append(s.charAt(i));
			}  
			System.out.println(ch2_return);
			};
			EverySecond.accept("1234567890");
			

		
		
		UnaryOperator<String> EverySecondContinued=s->{
			StringBuilder SBReturn=new StringBuilder();
			for(int i=1;i<s.length();i+=2) {
				SBReturn.append(s.charAt(i));
			}
			return SBReturn.toString();
		};
		System.out.println(EverySecondContinued.apply("9949592611"));
		
		Predicate<Integer> second4=s->{
			
			return !(s%2==0);
		};
		System.out.println(EverySecondContinued.apply("8919802996"));
		
		
		
		
		everySecondCharacter(second4,"9573177110");

		
		
		
		Supplier<String> ch5=()->{
			String iLoveJava="I Love Java";
			return iLoveJava;
			};
		System.out.println(ch5.get());

		
		
		
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