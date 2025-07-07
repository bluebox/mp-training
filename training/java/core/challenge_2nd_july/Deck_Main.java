import java.util.List;

public class Deck_Main {
	public static void main(String[] args) {
		List<Card> deck=Card.getStandardDeck();
//		Card.printDeck(deck);
		Card.printDeck(deck, "4 row deck", 4);
	}

}