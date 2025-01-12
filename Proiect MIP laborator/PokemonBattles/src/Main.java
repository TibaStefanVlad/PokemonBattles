import Modules.GameMode;
import Modules.Player;

import java.util.Scanner;
import Interfaces.IGameMode;

public class Main {
    public static void main(String[] args) {
        Scanner choiseScanner = new Scanner(System.in);
        char choice;
        Player player = new Player();
        System.out.println("Welcome to my unfinished game. This game is based on Pokemon battles. That's all it is for now. I hope you enjoy going through it!");

        do {
            System.out.println("1. Help");
            System.out.println("2. Play");
            System.out.println("3. Leave");
            System.out.println("Your selection");
            choice = choiseScanner.next().charAt(0);

            switch (choice){
                case '1':
                    printHelp();
                    break;
                case '2':
                    gameModeSelection(choiseScanner);
                    return;
                case '3':
                    System.out.println("You left the game ;-;");
                    break;
                default:
                    System.out.println("PLease select a valid option");
                    break;
            }
        } while(choice != '3');
    }

    public static void printHelp(){
        System.out.println("If you are not familiar with Pokemon battles or Pokemon, this short guide is for you.\n" +
                "As a Player you are given the choice to select from 6 Pokemon. Each Pokemon has a specific element or type." +
                "     \nexample: Squirtle is a water type pokemon that specializes in water attacks. Each type or element has a weakness or an advantage over other types" +
                "\n\nSquirtle is a water type pokemon and is weak to grass type Pokemon. But it has advantage over fire type Pokemon. It's easy to understand. Water beats fire, grass lives from water etc." +
                "\nEach Pokemon has a move/attack set. Each move/attack, besides damage points, has or has not a bonus effect on the target or on the user" +
                "\nLet's take a look at Squirtle's attack set. He learns a water move Bubble and a normal type move Tackle. Bubble is weaker in damage points, but has a chance to lower the target's defense" +
                "\nThis bonus effect gives an advantage to the user. Tackle may be more powerful in damage points, but does not have a bonus effect." +
                "\n\nStats: Each Pokemon has its unique stat spread. Attack stat decides how much power that pokemon has. Health Points or HP is the health of the Pokemon. If it reaches 0, that Pokemon is\n" +
                "unable to battle. Defense is the toughness of the Pokemon, which decides how much it can endure an attack. Speed decides which Pokemon is going to hit first, which is crucial in some cases." +
                "\n\nSome Pokemon are more Passive, being focused on defense and HP, but the speed stat is cut, while others are focused on hitting fast and hard, but are very frail. You can decide which one fits\n yout style" +
                "\nThe battle itself is just choosing an Attack to be used against your opponent's pokemon, until one of them faints.\n" +
                "All the types so far:\n- Grass\n- Fire\n- Water\n- Normal\n- Rock\n- Electric\n" +
                "\nStatus Conditions:\nAs I said earlier, some attacks have effects. Those effects might apply a status condition. What is that you ask?\n" +
                "Well, a status condition is a 'sickness' that the target gets.\n" +
                "example: A fire attack has a chance of inflicting the status condition called burn, which lowers the targets attack stat and also does additional damage each turn. At this moment you cannot cure it because I didn't\n" +
                "add any items in this game (yet), so you have a huge advantage.\n" +
                "Seeded is another status condition that is inflicted only by a specific move, called Leech seed. It has 100% chance to inflict that effect and what it does is the target takes additional damage each turn, and the user\n" +
                " then heals with the same amount of points the damage dealt to the target. Overpowered, right? Well I balanced that move by inflicting 0 damage. So the only damage done by that move is the effect it applied ((:\n\n");

    }



    public static void gameModeSelection(Scanner scanner){

        byte gameModeSelection;
        do {
            System.out.println("1. SingleBattle (1v1)");
            System.out.println("2. Survival (10 random battles, no healing after each battle)");
            System.out.println("3. Help");
            System.out.println("4. Exit");
            gameModeSelection = scanner.nextByte();

            IGameMode game = GameMode.none;


            switch (gameModeSelection){
                case 1:
                    game.setGameOption(GameMode.SingleBattle);
                    game.selectGameMode(game.getGameMode());
                    break;
                case 2:
                    System.out.println("Game mode unavailable");
                    break;
                case 3:
                    game.printGameModeHelp();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Please choose a valid game mode");
                    break;
            }
        } while (gameModeSelection != 4 && gameModeSelection != 1 && gameModeSelection != 2 && gameModeSelection != 3);
    }
}