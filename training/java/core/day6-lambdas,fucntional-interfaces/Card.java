package day6;

import java.util.ArrayList;
import java.util.List;

public class Card {
	private Suit suit;
	private String face;
	private int rank;

	public Card(Suit suit, String face, int rank) {
		this.suit = suit;
		this.face = face;
		this.rank = rank;
	}

	public static Card getNumericCard(Suit suit, int number) {
		return new Card(suit, String.valueOf(number), number);
	}

	public static Card getFaceCard(Suit suit, char abbrev) {
		int rank = "JQKA".indexOf(abbrev) + 11;
		return new Card(suit, String.valueOf(abbrev), rank);
	}

	public static List<Card> getStandardDeck() {
		List<Card> deck = new ArrayList<>(52);
		for (Suit suit : Suit.values()) {
			for (int i = 2; i <= 10; i++) {
				deck.add(getNumericCard(suit, i));
			}
			for (char face : "JKQA".toCharArray()) {
				deck.add(getFaceCard(suit, face));
			}
		}
		return deck;
	}

	@Override
	public String toString() {
		return String.format("%s %s (%d)", face, suit.getImage(), rank);
	}

	public static void printDeck(List<Card> deck) {
		deck.forEach(System.out::println);
	}

	public static void printDeck(List<Card> deck, String description, int rows) {
		System.out.printf("-----------%s-----------\n", description);
		for (int i = 0; i < deck.size(); i++) {
			System.out.print(deck.get(i)+" ");
			if ((i + 1) % rows == 0) {
				System.out.println();
			}
		}
	}

	@Override
	public int hashCode() {
		return rank;
	}
	
	
}
