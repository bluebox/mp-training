package day6;

public class OverrideHashCode {

	public static void main(String[] args) {
		Card c1=Card.getNumericCard(Suit.CLUB, 4);
		System.out.println(c1.hashCode());
	}

}
