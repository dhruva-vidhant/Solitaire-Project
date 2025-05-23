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
        if(firstCard==null) return lastCard.getNumber() == 13;
        return (firstCard.getNumber() == lastCard.getNumber()-1) && (firstCard.isBlack() != lastCard.isBlack());
    }

    //assumes card in wastePile
    public boolean wasteToFoundation(int idx) {
        boolean result = foundationPiles[idx].addCard(wastePile.peek());
        if(result) wastePile.pop();
        return result;
    }

    //assumes pileIdx is valid and is not empty
    public boolean tableauToFoundation(int pileIdx, int foundationIdx) {
        Pile p = piles[pileIdx];
        boolean result = foundationPiles[foundationIdx].addCard(p.removeLast());
        if(result) p.remove(p.size()-1);
        return result;
    }

    //assumes there is a card in wastePile and that pile is valid
    public boolean moveCardToPile(int pile) {
        Pile to = piles[pile];
        if(isValid(wastePile.peek(), to.get(to.size()-1))) {
            piles[pile].add(wastePile.pop());
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

    //assumes valid fromIdx and piles
    public boolean moveCards(int fromPile, int fromIdx, int toPile) {
        Pile from = piles[fromPile];
        Pile to = piles[toPile];
        if(isValid(from.isEmpty() ? null : from.get(fromIdx), to.get(to.size()-1)) && fromIdx < from.size()) {
            piles[toPile].add(piles[fromPile].removeCards(fromIdx));
            return true;
        }
        return false;
    }

    public void printState() {
        System.out.print(deck.isEmpty() ? "  " : "[]"); //Grab deck
        System.out.print("   ");
        System.out.print(wastePile.isEmpty() ? "   " : wastePile.peek()); //Grab deck left over
        System.out.print("    ");
        System.out.println("♠:♣:" + "\u001B[31m♥" + "\u001B[30m:" + "\u001B[31m♦" + "\u001B[30m:");
        System.out.print("            ");
        for(FoundationPile fp : foundationPiles) System.out.printf("%s", fp.peek() != null ? fp.peek() : "[]"); //Suit decks
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
