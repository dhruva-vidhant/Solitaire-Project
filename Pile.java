import java.util.ArrayList;
import java.util.List;

//represents a pile of cards
public class Pile extends ArrayList<Card> {
    private int firstVisible;

    //"num" is the number of cards that a pile will be initialized with
    public Pile(RandomAccessArray<Card> allCards, int num) {
        for(int i = 0; i < num; i++) {
            add(allCards.removeRandom());
            //else add(new Card(Card.Suit.CLUBS, 10));
        }
        firstVisible = size()-1;
    }

    public void add(List<Card> newCards) {
        addAll(newCards);
    }

    //"startIdx" is the index of the first card that is removed out of the pile that this object represents
    public List<Card> removeCards(int startIdx) {
        List<Card> result = new ArrayList<>(subList(startIdx, size()));
        removeRange(startIdx, size());
        firstVisible = startIdx-1;
        return result;
    }

    //"idx" means the index of a card in a pile
    public String display(int idx) {
        if(idx >= size()) return "  ";
        if(idx < firstVisible) return "[]";
        return get(idx).toString();
    }
}
