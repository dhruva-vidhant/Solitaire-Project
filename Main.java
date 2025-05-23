//import java
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        String msg = """
                Choose an action:
                1. Draw a new card from the deck
                2. Move a card from the waste pile to the table
                3. Move cards from one pile to another
                4. Move a card from the tableau to a foundation (suit) pile
                5. Move a card from the waste pile to a foundation (suit) pile
                6. Quit
                """;
        try(Scanner sc = new Scanner(System.in)) {
            String errorMsg = "Sorry, that is not a valid move. Please try again.";
            while(true) {
                game.printState();
                System.out.println(msg);
                switch(sc.nextLine()) {
                    case "1" -> game.drawCard();
                    case "2" -> {
                        System.out.println("Input the pile you want to move it to (from 1-7)");
                        int pile = sc.nextInt()-1;
                        if(!game.moveCardToPile(pile)) System.out.println(errorMsg);
                    }
                    case "3" -> {
                        System.out.println("Input the starting pile (from 1-7)");
                        int fromPile = sc.nextInt()-1;
                        System.out.println("Input which card in that pile you want to move");
                        int fromIdx = sc.nextInt()-1;
                        System.out.println("Input the destination (from 1-7)");
                        int toPile = sc.nextInt()-1;
                        if(!game.moveCards(fromPile, fromIdx, toPile)) {
                            System.out.println(errorMsg);
                        }
                    }
                    case "4" -> {
                        System.out.println("From which pile?");
                        int pile = sc.nextInt()-1;
                        System.out.println("And to which foundation pile?");
                        int foundation = sc.nextInt()-1;
                        if(!game.tableauToFoundation(pile, foundation)) System.out.println(errorMsg);
                        if(game.isWon()) {
                            System.out.println("Congrats, you won!");
                            return;
                        }
                    }
                    case "5" -> {
                        System.out.println("To which foundation pile?");
                        int foundation = sc.nextInt()-1;
                        if(!game.wasteToFoundation(foundation)) System.out.println(errorMsg);
                        if(game.isWon()) {
                            System.out.println("Congrats, you won!");
                            return;
                        }
                    }
                    case "6" -> {
                        System.out.println("Thanks for playing!");
                        return;
                    }
                    default -> {
                        System.out.println("Please type a valid number between 1 and 6");
                    }
                }   
            }
        }
        /*game.drawCard();
        game.printState();
        System.out.println(game.moveCardToPile(sc.nextInt()));
        game.printState();
        System.out.println(game.moveCards(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        game.printState();
        System.out.println(game.wasteToFoundation(sc.nextInt()));
        game.printState();
        System.out.println(game.tableauToFoundation(sc.nextInt(), sc.nextInt()));
        game.printState();
        sc.close();*/
    }
}
