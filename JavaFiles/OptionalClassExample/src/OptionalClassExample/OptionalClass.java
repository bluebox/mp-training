package OptionalClassExample;

import java.util.Optional;

public class OptionalClass {

	public static void main(String[] args) {
		
		String[] s= new String[10];
		
		
		Optional<String>opt = Optional.ofNullable(s[5]);
		
		if(opt.isPresent()) {
			String word= s[5].toLowerCase();
			
			System.out.println(word);
			
		}else {
			System.out.println("The word is null");
		}
		

	}

}
