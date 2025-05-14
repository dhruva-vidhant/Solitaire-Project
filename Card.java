public class Card {
    public static final String nums = "A123456789JQK";

    public enum Suit {
        SPADES(true), CLUBS(true), HEARTS(false), DIAMONDS(false);

        public final boolean isBlack;

        Suit(boolean color) {
            isBlack = color;
        }

        public String toString() {
            return switch(this) {
                case SPADES -> "♠";
                case CLUBS -> "♣";
                case HEARTS -> "♥";
                case DIAMONDS -> "♦";
            };
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

    public String toString() {
        char numChar = nums.charAt(number-1);
        String suitChar = suit.toString();
        return numChar + suitChar;
    }
}