import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;
//Represents a game, with deck and piles
public class Game {
    private Deque<Card> deck = new ArrayDeque<>();
    private Deque<Card> wastePile = new ArrayDeque<>();
    private FoundationPile[] foundationPiles = new FoundationPile[4];
    private static int numCols = 5; 
    private Pile[] piles = new Pile[numCols];

    public Game() {
        Card.Suit[] suits = Card.Suit.values();
        for(int i = 0; i < 4; i++) {
            foundationPiles[i] = new FoundationPile(suits[i]);
        }
        var allCards = allCards();
        for(int i = 1; i <= numCols; i++) {
            piles[i-1] = new Pile(allCards, i);
        }
        Card c;
        while((c=allCards.removeRandom()) != null) {
            deck.push(c);
        }
    }

    /*public void testCard() {
        wastePile.push(new Card(Card.Suit.CLUBS, 10));
        wastePile.push(new Card(Card.Suit.DIAMONDS, 11));
        for(Card c : deck) System.out.println(c);
        for(int i = 0; i <= deck.size(); i++) drawCard();
        System.out.println();
        for(Card c : deck) System.out.println(c);
    }*/

    public void drawCard() {
        if(!deck.isEmpty()) wastePile.push(deck.pop());
        else {
            deck = new ArrayDeque<>(wastePile.size());
            Iterator<Card> iter = wastePile.descendingIterator();
            for(Card c : (Iterable<Card>) () -> iter) {
                deck.push(c);
            }
            wastePile = new ArrayDeque<>();
        }
    }

    private boolean isValid(Card firstCard, Card lastCard) {
        if(lastCard==null) return firstCard.getNumber() == 13;
        return (firstCard.getNumber() == lastCard.getNumber()-1) && (firstCard.isBlack() != lastCard.isBlack());
    }

    public boolean wasteToFoundation(int idx) {
        if(wastePile.isEmpty() || invalidFound(idx)) return false;
        boolean result = foundationPiles[idx-1].addCard(wastePile.peek());
        if(result) wastePile.pop();
        return result;
    }

    private boolean invalidFound(int idx) {
        return idx < 1 || idx > 4;
    }

    private boolean invalidPile(int idx) {
        return idx < 1 || idx > numCols;
    }

    public boolean tableauToFoundation(int pileIdx, int foundationIdx) {
        if(invalidPile(pileIdx) || invalidFound(foundationIdx)) return false;
        if(piles[pileIdx-1].isEmpty()) return false;
        Pile p = piles[pileIdx-1];
        boolean result = foundationPiles[foundationIdx-1].addCard(p.getLast());
        if(result) p.removeLast();
        return result;
    }

    public boolean moveCardToPile(int pile) {
        if(wastePile.isEmpty() || invalidPile(pile)) return false;
        Pile to = piles[pile-1];
        if(isValid(wastePile.peek(), to.get(to.size()-1))) {
            to.add(wastePile.pop());
            return true;
        }
        return false;
    }

    public boolean isWon() {
        for(var p : foundationPiles) {
            if(!p.isDone()) return false;
        }
        return true;
    }

    public boolean moveCards(int fromPile, int fromIdx, int toPile) {
        if(invalidPile(fromPile) || invalidPile(toPile)) return false;
        Pile from = piles[fromPile-1];
        Pile to = piles[toPile-1];
        if(fromIdx > from.size()) return false;
        if(isValid(from.get(fromIdx-1), to.isEmpty() ? null : to.get(to.size()-1))) {
            to.add(from.removeCards(fromIdx-1));
            return true;
        }
        //throw new RuntimeException(from.get(fromIdx-1).toString());
        return false;
    }

    public void printState() {
        System.out.print(deck.isEmpty() ? "  " : "[]"); //Grab deck
        System.out.print("   ");
        System.out.print(wastePile.isEmpty() ? "   " : wastePile.peek()); //Grab deck left over
        System.out.print("    ");
        System.out.println("♠: ♣: " + "\u001B[31m♥ " + "\u001B[30m:" + "\u001B[31m♦ " + "\u001B[30m:");
        System.out.print("            ");
        for(FoundationPile fp : foundationPiles) System.out.printf("%s", fp.peek() != null ? fp.peek() : "[ ]"); //Suit decks
        System.out.println();
        System.out.println("  1  2  3  4  5"); //Col nums
        int largestLen = piles[0].size();
        for(int i = 0; i < numCols; i++)
            if(piles[i].size() > largestLen) largestLen = piles[i].size();
        for(int i = 0; i < largestLen; i++) { //Print Cols
            System.out.print(i + 1 + " ");
            for(int j = 0; j < piles.length; j++) {
                System.out.printf("%s", piles[j].display(i));
            }
            System.out.println();
        }
        //System.out.println("Deck: ");
        //for(Card c : deck) System.out.print(c + " ");
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
