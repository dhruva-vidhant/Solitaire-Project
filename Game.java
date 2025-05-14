public class Game {
    private ArrayList<Card> Deck;
    private ArrayList<Pile> piles;

    public Game() {
        var allCards = allCards();
        
    }

    private static RandomAccessArray<Card> allCards() {
        var allCards = new RandomAccessArray<Card>();
        Suit[] suits = Suit.values();
        for(Suit s : suits) {
            for(int i = 1; i <= 13; i++) {
                allCards.add(new Card(s, i));
            }
        }
        return allCards;
    }
}