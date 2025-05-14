import java.util.ArrayList;
import java.util.List;

//represents a pile of cards
public class Pile extends ArrayList<Card> {
    private int firstVisible;

    public Pile(RandomAccessArray<Card> allCards, int num) {
        for(int i = 0; i < num; i++) {
            super.add(allCards.removeRandom());
        }
        firstVisible = size()-1;
    }

    public boolean add(Card newCard) {
        if(newCard.getNumber() != getLast().getNumber() || newCard.isBlack() == getLast().isBlack()) {
            return false;
        }
        return super.add(newCard);
    }

    public boolean add(List<Card> newCards) {
        Card first = newCards.get(0);
        if(first.getNumber() != getLast().getNumber() || first.isBlack() == getLast().isBlack()) {
            return false;
        }
        return addAll(newCards);
    }

    public List<Card> removeCards(int startIdx) {
        List<Card> result = subList(startIdx, size());
        removeRange(startIdx, size());
        firstVisible = startIdx-1;
        return result;
    }
    
    public String display(int idx) {
        if(idx >= size()) return "  ";
        if(idx < firstVisible) return "[]";
        return get(idx).toString();
    }
}