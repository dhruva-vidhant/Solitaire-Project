import java.util.ArrayDeque;
public class FoundationPile {
    private ArrayDeque<Card> cards = new ArrayDeque<>();
    private int lastNum;
    private final Card.Suit suit;

    public FoundationPile(Card.Suit suit) {
        this.suit = suit;
    }

    public boolean addCard(Card c) {
        if((c.getNumber() != lastNum+1) || (c.getSuit() != suit)) return false;
        cards.push(c);
        lastNum++;
        return true;
    }

    public boolean isDone() {return lastNum == 13;}

    public Card peek() {return cards.peek();}

}
