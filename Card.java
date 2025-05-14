public class Card {
    public enum Suit {
        SPADES(true), CLUBS(true), HEARTS(false), DIAMONDS(false);

        public final boolean isBlack;

        Suit(boolean color) {
            isBlack = color;
        }
    }
    private Suit suit;
    private int number;

    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public Suit getSuit() {return suit;}
    public int getNumber() {return number;}
}