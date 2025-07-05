
public class RepresentationOfNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printNumberInWord(5);
	}
	public static void printNumberInWord(int i)
	{
		switch(i)
		{
		case 0->System.out.println("ZERO");
		case 1->System.out.println("ONE");
		case 2->System.out.println("TWO");
		case 3->System.out.println("THREE");
		case 4->System.out.println("FOUR");
		case 5->System.out.println("FIVE");
		case 6->System.out.println("SIX");
		case 7->System.out.println("SEVEN");
		case 8->System.out.println("EIGHT");
		case 9->System.out.println("NINE");
		default->System.out.println("OTHER");
		}
	}

}

