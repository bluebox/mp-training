import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckOfcards {
	static class Card
	{
		String suit;
		String rank;
		public Card(String rank,String suit)
		{
			this.rank=rank;
			this.suit=suit;
		}
		public String toString()
		{
			return rank+"of "+suit;
		}
	}
	public static void main(String[] args)
	{
		List<Card>deck =createDeck();
		Collections.shuffle(deck);

		int players = 3;
        int cardsPerPlayer = 6;
        List<List<Card>> hands = dealHands(deck, players, cardsPerPlayer);

        for (int i = 0; i < players; i++) {
            System.out.println("Player " + (i + 1) + "'s hand: " + hands.get(i));
            evaluateHand(hands.get(i));
        }
	}
	private static void evaluateHand(List<DeckOfcards.Card> list) {
		// TODO Auto-generated method stub
		Map<String,Integer> ranks=new HashMap<>();
		for(Card cards:list)
		{
			ranks.put(cards.rank, ranks.getOrDefault(cards.rank, 0)+1);
		}
		for(Map.Entry<String, Integer> i:ranks.entrySet())
		{
			if(i.getValue()>1)
			{
				System.out.println(" - pair of "+i.getKey());
			}
		}
		
	}
	private static List<List<DeckOfcards.Card>> dealHands(List<DeckOfcards.Card> deck, int players,
			int cardsPerPlayer) {
		// TODO Auto-generated method stub
			List<List<Card>> hands=new ArrayList<>();
			for(int i=0;i<players;i++)
				hands.add(new ArrayList<>());
			for(int i=0;i<cardsPerPlayer*players;i++)
				hands.get(i%players).add(deck.get(i));
		return hands;
	}
	private static List<DeckOfcards.Card> createDeck() {
		// TODO Auto-generated method stu
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        List<Card> deck=new ArrayList<>();
        for(String s:suits)
        {
        	for(String r:ranks)
        	{
        		deck.add(new Card(r,s));
        	}
        }
        
		return deck;
	}
}
