import java.util.stream.Stream;

public class StreamBingo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int start=1;
		Stream.iterate(start,i->i<=15,i->i+1)
				.map(i->"B"+i)
		.forEach(s->System.out.println(s));
		start+=15;
		Stream.iterate(start,i->i+1)
			.limit(15)
			.map(i->"I"+i)
			.forEach(s->System.out.println(s));
			start+=15;
		Stream.iterate(start,i->i+1)
			  .limit(15)
		      .map(i->"N"+i)
              .forEach(s->System.out.println(s));
               start+=15;
        Stream.iterate(start,i->i+1)
        	  .limit(15)
 		      .map(i->"G"+i)
              .forEach(s->System.out.println(s));
               start+=15; 
        Stream.iterate(start,i->i+1)
        	  .limit(15)
 		       .map(i->"O"+i)
               .forEach(s->System.out.println(s));   
	}

}
