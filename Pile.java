import java.util.ArrayList;

public class Pile {
    private ArrayList<Card> cards = new ArrayList<>();
    private int firstVisible;
    
    public Pile(RandomAccessArray<Card> allCards, int num) {
        for(int i = 0; i < num; i++) {
            cards.add(allCards.removeRandom());
        }
        firstVisible = cards.size()-1;
    }

    public void add(ArrayList<Card> newCards) {
        cards.addAll(newCards);
    }

    public ArrayList<Card> remove(int startIdx) {
        ArrayList<Card> result = cards.subList(startIdx, cards.size());
        //cards.removeRange(startIdx, cards.size());
    }
}