package Day6;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lambda {
	public static void main(String[] args) {
		String[] names=new String[]{"Babu","guru","ravi","Suri","cHaRRi"};
		Function<String,String> change=String::toUpperCase;
		UnaryOperator<String> Intial=name->{
			char intial=(char)('A'+new Random().nextInt(26));
			return name+' '+intial+' ';
		};
		UnaryOperator<String> revName=name->{
			String[] parts=name.split(" ");
			String lastName=new StringBuilder(parts[0]).reverse().toString();
			return name+lastName;
		};
		
		for(int i=0;i<names.length;i++) {
			String name=names[i];
			name=change.apply(name);
			name=Intial.apply(name);
			name=revName.apply(name);
			names[i]=name;
		}
		
		List<String> namesList=Arrays.asList(names);
		namesList.forEach(name-> System.out.println(name) );
	}
}

