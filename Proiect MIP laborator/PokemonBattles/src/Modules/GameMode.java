package Modules;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Interfaces.IGameMode;

public enum GameMode implements IGameMode {
    SingleBattle,
    Survival,
    ChangeOrderOfPokemon,
    none;

    public static GameMode option;
    Player player = new Player();


    @Override
    public void setGameOption(GameMode playersOption){
        option = playersOption;
    }


    @Override
    public GameMode getGameMode(){
        return option;
    }

    @Override
    public void selectGameMode(GameMode game) {
        switch (game.option) {
            case Survival:
                int battlesFought = 0;
                while (battlesFought < 10){
                    battlesFought++;
                }
            case SingleBattle:
                Player player1 = new Player();
                Player player2 = new Player();

                System.out.println("Player1 setup: ");
                createPlayerSetup(player1);
                System.out.println("Player2 setup: ");
                createPlayerSetup(player2);

                Battle battle = new Battle(player1, player2);
                if (battle.beginBattle(player1, player2) == player1){
                    System.out.println(player1.getName() + " is the winner!");

                }
                else{
                    System.out.println(player2.getName() + " is the winner!");
                }
                break;
            case ChangeOrderOfPokemon:

                int option1, option2, option3;
                Scanner optionScanner = new Scanner(System.in);

                do {
                    option3 = optionScanner.nextInt();
                    player.showPokemonOwned();
                    System.out.println("1. Change order of pokemon\n2. Return");
                    switch (option3){
                        case 1:
                            System.out.println("Choose 2 pokemon to switch places with each other\n");
                            System.out.print("Pokemon number ");
                            option1 = optionScanner.nextInt();
                            System.out.print(option1);

                            System.out.print("\nPokemon number ");
                            option2 = optionScanner.nextInt();
                            if (option1 - 1 >= 0 && option1 - 1 <= player.m_pokemonsOwned.size() ||
                                    option1 - 2 >= 0 && option1 - 2 <= player.m_pokemonsOwned.size())
                            {
                                player.changeOrderOfPokemon(player.m_pokemonsOwned.get(option1),
                                        player.m_pokemonsOwned.get(option2), option1, option2);
                            }
                            else{
                                System.out.println("Invalid options");
                            }
                    }
                } while (option3 != 1);
                break;

            default:
                System.out.println("You cannot play this game Mode yet");
                break;
        }
    }

    @Override
    public void createPlayerSetup(Player newPlayer){
        String newName;
        byte pokemonSelection;
        Random randomIdGenerator = new Random();

        Scanner playerScanner = new Scanner(System.in);
        System.out.print("Enter your name please\n" +
                "Name: ");
        newName = playerScanner.nextLine();
        newPlayer.m_name = newName;
        newPlayer.m_PlayerID = randomIdGenerator.nextInt(100,999);

        newPlayer.m_pokemonsOwned = new ArrayList<>();
        newPlayer.m_pokemonsOwned.add(new Pokemon());

        System.out.println("\n\nChoose a pokemon from these 3:");
        System.out.print("1. Bulbasaur (Grass type)\n" +
                            "2. Charmander (Fire type)\n" +
                            "3. Squirtle (Water type)\n" +
                            "Choose Wisely: ");

        do{
            pokemonSelection = playerScanner.nextByte();
            switch (pokemonSelection){
                case 1:
                    newPlayer.m_pokemonsOwned.get(0).getPokemonFromFile(returnPokemonFile("Bulbasaur.txt"), newPlayer);
                    System.out.println("Congrats! You chose Bulbasaur to be your pokemon!\n\n");
                    break;
                case 2:
                    newPlayer.m_pokemonsOwned.get(0).getPokemonFromFile(returnPokemonFile("Charmander.txt"), newPlayer);
                    System.out.println("Congrats! You chose Charmander to be your pokemon!\n\n");
                    break;
                case 3:
                    newPlayer.m_pokemonsOwned.get(0).getPokemonFromFile(returnPokemonFile("Squirtle.txt"), newPlayer);
                    System.out.println("Congrats! You chose Squirtle to be your pokemon!\n\n");
                    break;
                case 4:
                    System.out.println("You didn't choose a pokemon. You chose to leave.... Game aborted ;-;");
                    return;
                default:
                    System.out.println("Please choose a valid Pokemon");

            }
        } while (pokemonSelection != 3  & pokemonSelection != 2 & pokemonSelection != 1);

        newPlayer.m_activeSpot = newPlayer.m_pokemonsOwned.getFirst();
    }

    @Override
    public String returnPokemonFile(String fileName){
        return "src\\Modules\\PokemonFiles\\" + fileName;
    }

    @Override
    public void printGameModeHelp(){
        System.out.println("Welcome to game mode selection. Unfortunately there is only one game mode available because the creator didn't work\n" +
                "in a rush. So the only game mode available is Single battle. We're sorry for the inconvenience ;-;\n\n");
    }

}
