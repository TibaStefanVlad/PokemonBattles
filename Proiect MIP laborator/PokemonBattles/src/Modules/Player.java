package Modules;

import Modules.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    public String m_name;
    public int m_PlayerID;
    public List<Pokemon> m_pokemonsOwned;
    public Pokemon m_activeSpot;

    public Player(){
        m_pokemonsOwned = new ArrayList<>();
    }

    public Player(Player other){
        m_name = other.m_name;
        m_PlayerID = other.m_PlayerID;
        m_pokemonsOwned = other.m_pokemonsOwned;
        m_activeSpot = m_pokemonsOwned.getFirst();
    }

    public String getName(){
        return m_name;
    }

    public int getPID(){
        return m_PlayerID;
    }


    public boolean isOutOfPokemon(){
        /*
        int count = 0;
        for (int i = 0; i < m_pokemonsOwned.size(); i++){
            if (m_pokemonsOwned.get(i).m_stats.getRemainingHp() <= 0.0f){
                count++;
            }

        }
        if (count == m_pokemonsOwned.size()){
            return true;
        }
        */
        if (m_pokemonsOwned.getFirst().m_stats.getRemainingHp() <= 0.0f){
            return true;
        }
        return false;
    }

    /*
    public void selectFirstPokemon(){
        int number = 1;
        Pokemon starter = new Pokemon();
        switch (number){
            case 1:
                m_pokemonsOwned.add(starter.getPokemonFromFile("../../PokemonFiles/Charmander.txt", this));
                break;
            default:
                System.out.println("Invalid option");
        }

    }
*/
    public void showPokemonOwned(){
        int i;
        for (i = 0; i < m_pokemonsOwned.size(); i++){
            System.out.println((i + 1) + m_pokemonsOwned.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 0 to continue...");
        i = scanner.nextInt();

        return;
    }

    public void changeOrderOfPokemon(Pokemon change1, Pokemon change2, int index1, int index2){
        if (index1 == 0){
            m_activeSpot = m_pokemonsOwned.get(index2);
        }
        else if (index2 == 0){
            m_activeSpot = m_pokemonsOwned.get(index1);
        }
        Pokemon aux = new Pokemon(change1);
        m_pokemonsOwned.set(index1, change2);
        m_pokemonsOwned.set(index2, aux);
    }

    public boolean changePokemonInActiveSpot(Pokemon newActivePokemon, int indexOfNewActive){
        if (indexOfNewActive == -1){
            for (int i = 1; i < m_pokemonsOwned.size(); i++){
                if (m_pokemonsOwned.get(i).m_stats.getRemainingHp() > 0){
                    changeOrderOfPokemon(newActivePokemon, m_pokemonsOwned.get(i), 0, i);
                    m_activeSpot = m_pokemonsOwned.getFirst();
                    return true;
                }
            }
            if (m_activeSpot.m_stats.getRemainingHp() == 0)
                return false;
        }

        changeOrderOfPokemon(m_activeSpot, newActivePokemon, 0, indexOfNewActive);
        return true;
    }
}
