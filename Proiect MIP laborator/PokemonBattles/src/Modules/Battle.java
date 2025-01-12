package Modules;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Battle {

    public Player player1;
    public Player player2;

    public Battle(Player other1, Player other2){
        player1 = other1;
        player2 = other2;
    }

    public Battle(Battle other){
        player1 = other.player1;
        player2 = other.player2;
    }

    public Player beginBattle(Player player1, Player player2){
        Player winner = new Player();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            if (player1.m_activeSpot.m_stats.getRemainingHp() <= 0){
                player1.changePokemonInActiveSpot(null, -1);
            }
            else if (player2.m_activeSpot.m_stats.getRemainingHp() <= 0){
                player2.changePokemonInActiveSpot(new Pokemon(), -1);
            }

            int dmgFromPlayer1 = 0;
            int dmgFromPlayer2 = 0;

            PriorityQueue<Attacks> attacksChosenByPlayers= new PriorityQueue<Attacks>(new Comparator<Attacks>() {
                @Override
                public int compare(Attacks o1, Attacks o2) {
                    return Integer.compare((int)o2.getUser().m_stats.getSpeed(), (int)o1.getUser().m_stats.getSpeed()) ;
                }
            });

            printBattleSceneForPlayer(player1, player2);

            takeTurn(player1, attacksChosenByPlayers, scanner);

            printBattleSceneForPlayer(player2, player1);
            takeTurn(player2, attacksChosenByPlayers, scanner);

            if (attacksChosenByPlayers.peek().getUser().getOwner() == player1){
                dmgFromPlayer1 = player1.m_activeSpot.getDamageOfAnAttack(player2.m_activeSpot
                                                                                        , attacksChosenByPlayers.peek());
                attacksChosenByPlayers.peek().applyEffect(player1.m_activeSpot, player2.m_activeSpot);
                attacksChosenByPlayers.remove(attacksChosenByPlayers.peek());

                player2.m_activeSpot.changeHPRemaining(dmgFromPlayer1);
                System.out.println(player1.m_activeSpot.getName() +  " dealt " + dmgFromPlayer1 + " dmg to "
                        + player2.m_activeSpot.getName());

                if (player2.m_activeSpot.m_stats.getRemainingHp() > 0){
                    dmgFromPlayer2 = player2.m_activeSpot.getDamageOfAnAttack(player1.m_activeSpot,
                    attacksChosenByPlayers.peek());

                    attacksChosenByPlayers.remove(attacksChosenByPlayers.peek());
                    player1.m_activeSpot.changeHPRemaining(dmgFromPlayer2);
                    System.out.println(player2.m_activeSpot.getName() +  " dealt " + dmgFromPlayer2 + " dmg to "
                            + player1.m_activeSpot.getName());
                }
            }
            else {
                dmgFromPlayer2 = player2.m_activeSpot.getDamageOfAnAttack(player1.m_activeSpot
                        , attacksChosenByPlayers.peek());
                attacksChosenByPlayers.peek().applyEffect(player2.m_activeSpot, player1.m_activeSpot);
                attacksChosenByPlayers.remove(attacksChosenByPlayers.peek());

                player1.m_activeSpot.changeHPRemaining(dmgFromPlayer2);
                System.out.println(player2.m_activeSpot.getName() +  " dealt " + dmgFromPlayer2 + " dmg to "
                        + player1.m_activeSpot.getName());

                if (player1.m_activeSpot.m_stats.getRemainingHp() > 0){
                    dmgFromPlayer1 = player1.m_activeSpot.getDamageOfAnAttack(player2.m_activeSpot
                            , attacksChosenByPlayers.peek());
                    attacksChosenByPlayers.remove(attacksChosenByPlayers.peek());

                    player2.m_activeSpot.changeHPRemaining(dmgFromPlayer1);
                    System.out.println(player1.m_activeSpot.getName() +  " dealt " + dmgFromPlayer1 + " dmg to "
                            + player2.m_activeSpot.getName());
                }
            }

            player1.m_activeSpot.applyingEffectDMG(player2.m_activeSpot);
            player2.m_activeSpot.applyingEffectDMG(player1.m_activeSpot);

            if (!attacksChosenByPlayers.isEmpty()){
                attacksChosenByPlayers.clear();
            }

            player1.m_pokemonsOwned.getFirst().m_stats.setRemainingHp(player1.m_activeSpot.m_stats.getRemainingHp());
            player2.m_pokemonsOwned.getFirst().m_stats.setRemainingHp(player2.m_activeSpot.m_stats.getRemainingHp());

            System.out.println("-------------------------------------------------------");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");System.out.println("\n");System.out.println("\n");

        } while (!(player1.isOutOfPokemon() || player2.isOutOfPokemon()));

        if (player1.isOutOfPokemon()){
            System.out.println(player1.getName() + " is out of pokemon");
            winner = player2;
        }
        else{
            System.out.println(player2.getName() + " is out of pokemon");
            winner = player1;
        }


        scanner.close();
        return winner;
    }

    public void printBattleSceneForPlayer(Player player1, Player player2){
        System.out.println("");
        System.out.println("\n");

        System.out.println(player1.m_name + "'s pokemon:" );
        player1.m_activeSpot.printPokemonInfo();
        System.out.println("\n");
        System.out.println(player2.m_name + "'s pokemon:" );
        player2.m_activeSpot.printPokemonInfo();
        System.out.println("\n");

        System.out.println("1. Fight");
        System.out.println("2. Bag");
        System.out.println("3. Pokemon");
        System.out.println("4. Leave");

        System.out.println(player1.m_name + "'s turn: ");
    }

    public void takeTurn(Player player1, PriorityQueue<Attacks> attacksChosenByPlayers, Scanner scanner) {

        byte battleOption;
        do {
            battleOption = scanner.nextByte();
            switch (battleOption) {
                case 1:
                    attacksChosenByPlayers.add(player1.m_activeSpot.chooseAttack());
                    break;
                case 2:
                    System.out.println("Not available at this moment");
                    break;
                case 3:
                    player1.showPokemonOwned();
                    break;
                case 4:
                    System.out.println("You quit the battle");  
                    break;
            }
        } while (battleOption != 1 && battleOption != 4);
    }

}
