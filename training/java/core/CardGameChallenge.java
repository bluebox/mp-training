import java.util.*;

public class CardGameChallenge {
    static class Card {
        String suit;
        String rank;

        Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String toString() {
            return rank + " of " + suit;
        }
    }

    public static void main(String[] args) {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        
        int players = 4;
        int cardsPerPlayer = 5;
        List<List<Card>> hands = dealHands(deck, players, cardsPerPlayer);

        for (int i = 0; i < players; i++) {
            System.out.println("Player " + (i + 1) + "'s hand: " + hands.get(i));
            evaluateHand(hands.get(i));
        }
    }

    static List<Card> createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        List<Card> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }

    static List<List<Card>> dealHands(List<Card> deck, int players, int cardsPerPlayer) {
        List<List<Card>> hands = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            hands.add(new ArrayList<>());
        }
        for (int i = 0; i < cardsPerPlayer * players; i++) {
            hands.get(i % players).add(deck.get(i));
        }
        return hands;
    }

    static void evaluateHand(List<Card> hand) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.rank, rankCount.getOrDefault(card.rank, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(" - Pair of " + entry.getKey());
            }
        }
    }
}
