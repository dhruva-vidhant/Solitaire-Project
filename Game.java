import java.util.ArrayList;
//Represents a game, with deck and piles
public class Game {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Pile> piles = new ArrayList<>();

    public Game() {
        var allCards = allCards();
        for(int i = 1; i <= 5; i++) {
            piles.add(new Pile(allCards, i));
        }
        Card c;
        while((c=allCards.removeRandom()) != null) {
            deck.add(c);
        }
    }

    public void addCardFromDeck(int pile) {
        piles.get(pile).add(deck.get(deck.size()-1));
    }

    public void moveCards(int fromPile, int fromIdx, int toPile) {
        piles.get(toPile).add(piles.get(fromPile).removeCards(fromIdx));
    }

    public void printState() {
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < piles.size(); j++) {
                System.out.print(piles.get(j).display(i));
            }
            System.out.println();
        }
        System.out.println("Deck: ");
        for(Card c : deck) System.out.print(c + " ");
        /*for(int i = 0; i < 5; i++) {
            System.out.println(piles.get(0).display(i));
        }*/
    }

    private static RandomAccessArray<Card> allCards() {
        var allCards = new RandomAccessArray<Card>(52);
        Card.Suit[] suits = Card.Suit.values();
        for(Card.Suit s : suits) {
            for(int i = 1; i <= 13; i++) {
                allCards.add(new Card(s, i));
            }
        }
        return allCards;
    }
}