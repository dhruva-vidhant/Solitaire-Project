import java.util.ArrayList;
import java.util.List;

public class Pile extends ArrayList<Card> {
    private int firstVisible;

    public Pile(RandomAccessArray<Card> allCards, int num) {
        for(int i = 0; i < num; i++) {
            add(allCards.removeRandom());
        }
        firstVisible = cards.size()-1;
    }

    public void add(List<Card> newCards) {
        addAll(newCards);
    }

    public List<Card> removeCards(int startIdx) {
        List<Card> result = subList(startIdx, cards.size());
        removeRange(startIdx, cards.size());
        firstVisible = startIdx-1;
        return result;
    }
    
    public String display(int idx) {
        if(idx < firstVisible) return "[]";
        return get(idx).toString();
    }
}