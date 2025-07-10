package July2;

import java.util.List;

public class Card {

	private Suit suit;
	private String face;
	private int rank;

	public static Card getNumericCard(Suit suit, int number) {

	}

	public static Card getFaceCard(Suit suit, char abbrev) {

	}

	public static List<Card> getStandardDeck() {

	}

	public static void printDeck(List<Card> deck) {

	}

	public static void printDeck(List<Card> deck, String description, int rows) {

	}

	@Override
	public String toString() {
		return "Card [suit=" + suit + ", face=" + face + ", rank=" + rank + "]";
	}
	
}
