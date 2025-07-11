package day_8_7_25;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dicegame {
     
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Random random = new Random();
        List<Integer> randomInts = random.ints(5, 1, 6).sorted().boxed().collect(Collectors.toList());
        System.out.print("random dice array : [");// 10 random ints between 0 and 99
             for(int num:randomInts) {
        	System.out.print(num);
        }
        System.out.println("]");
		System.out.println("Type 'All' to re roll all the Dice");
		System.out.println("List numbers (separated by spaces) to reroll selected dice");
		String input=sc.nextLine();
		if(input.equals("All")) {
			randomInts=random.ints(5, 1, 6).sorted().boxed().collect(Collectors.toList());
			randomInts
			.forEach(s->System.out.print(s));
		}else if(input.isEmpty()) {
            System.out.println("Game over , you can leave  or start again");
        }
		else {
			String arr[]=input.split(" ");
			for(String str:arr) {
				if(randomInts.contains(Integer.parseInt(str))) {
					randomInts.set(randomInts.indexOf(Integer.parseInt(str)), random.nextInt(5)+1);
				}
		}
		randomInts.forEach(System.out::print);		
	}
	}

}
