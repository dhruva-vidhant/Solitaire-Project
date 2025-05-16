//import java
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.printState();
        Scanner sc = new Scanner(System.in);
        /*while(true) {
            System.out.println("Type A to add a card to a pile, M to move cards between piles, and Q to quit");
            switch(sc.nextLine()) {
                case "A" -> {
                    System.out.println("Type which pile you want to add it to");
                    game.addCardFromDeck(sc.nextInt());
                };
                case "M" -> {
                    System.out.println("Type which pile you want to take it from");
                }
            }
        }*/
        game.drawCard();
        game.printState();
        System.out.println(game.moveCardToPile(sc.nextInt()));
        game.printState();
        System.out.println(game.moveCards(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        game.printState();
        sc.close();
    }
}