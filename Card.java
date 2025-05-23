//Represents a card with number and suit
public class Card {
    public static final String[] nums = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    //Reprents a Suit, with its representation and color
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
                case HEARTS -> "\u001B[31m♥" + "\u001B[30m";
                case DIAMONDS -> "\u001B[31m♦" + "\u001B[30m";
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

    public boolean isBlack() {
        return suit.isBlack;
    }

    public String toString() {
        String numChar = nums[number-1];
        String suitChar = suit.toString();
        String space = number == 10 ? "" : " ";
        return numChar + suitChar + space;
    }
}
