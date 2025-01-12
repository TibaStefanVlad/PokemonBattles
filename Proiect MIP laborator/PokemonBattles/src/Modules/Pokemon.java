package Modules;
import Modules.PokemonAttacks.*;

import java.util.ArrayList;
import java.util.List;

import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files

public class Pokemon {
    private String m_name;
    private Type m_type;
    public Stats m_stats;
    private Player m_owner;
    private List<Attacks> m_attacks;
    private boolean m_canEvolve;
    public StatusConditions m_statusCondition;
    private int m_level;
    /*
    private int m_exp;
    */

    public Pokemon(String name,
                   Type type,
                   Stats stats,
                   Player owner,
                   List<Attacks> attacks,
                   boolean canEvolve,
                   int level,
                   StatusConditions statusCondition,
                   int exp){
        m_name = name;
        m_type = type;
        m_stats = stats;
        m_owner = owner;
        m_attacks = attacks;
        m_canEvolve = canEvolve;
        m_level = level;
        m_statusCondition = statusCondition;
        //m_exp = exp;
    }

    public Pokemon(Pokemon other){
        m_name = other.getName();
        m_type = other.getType();
        m_stats = other.getStats();
        m_owner = other.getOwner();
        m_attacks = other.getAttacks();
        m_canEvolve = other.CanEvolve();
        m_level = other.m_level;
        m_statusCondition = other.m_statusCondition;
        //m_exp = other.m_exp;
    }

    public Pokemon(){
        m_attacks = new ArrayList<>();
        m_stats = new Stats();
        m_stats.setHp(0.0f);
    }

    public String getName() {
        return m_name;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public Type getType() {
        return m_type;
    }

    public void setType(Type m_type) {
        this.m_type = m_type;
    }

    public Player getOwner() {
        return m_owner;
    }

    public void setOwner(Player m_owner) {
        this.m_owner = m_owner;
    }

    public Stats getStats() {
        return m_stats;
    }

    public void setStats(Stats m_stats) {
        this.m_stats = m_stats;
    }

    public List<Attacks> getAttacks() {
        return m_attacks;
    }

    public void setAttacks(List<Attacks> m_attacks) {
        this.m_attacks = m_attacks;
    }

    public boolean CanEvolve() {
        return m_canEvolve;
    }

    public void setCanEvolve(boolean m_canEvolve) {
        this.m_canEvolve = m_canEvolve;
    }

    public int getM_level() {
        return m_level;
    }

    public void setM_level(int m_level) {
        this.m_level = m_level;
    }

    public Pokemon getPokemonFromFile(String fileName, Player owner){
        String canEvolve;
        String pokemonType;
        List<String> pokemonAttacks = null;
        String pokemonAttack;
        String StatusCondition;

        try{
            File pokemonFile = new File(fileName);
            Scanner pokemonReader = new Scanner(pokemonFile);

            m_name = pokemonReader.nextLine();
            pokemonType = pokemonReader.nextLine();

            switch(pokemonType){
                    case "Grass":
                    m_type = Type.Grass;
                    if (m_attacks.size() <= 3) {
                        m_attacks.add(new LeechSeed(this));
                        m_attacks.add(new Tackle(this));
                    }
                    break;
                    case "Fire":
                        m_type = Type.Fire;
                        if (m_attacks.size() <= 3) {
                            m_attacks.add(new Ember(this));
                            m_attacks.add(new Tackle(this));
                        }
                        break;

                    case "Water":
                        m_type = Type.Water;
                        if (m_attacks.size() <= 3) {
                            m_attacks.add(new Bubble(this));
                            m_attacks.add(new Tackle(this));
                        }
                        break;
                    case "Electric":
                        m_type = Type.Electric;
                        if (m_attacks.size() <= 3) {
                            m_attacks.add(new ThunderShock(this));
                            m_attacks.add(new Tackle(this));
                        }
                        break;
                    case "Normal":
                        m_type = Type.Normal;
                        if (m_attacks.size() <= 3) {
                            m_attacks.add(new Tackle(this));
                        }
                        break;
                    case "Rock":
                        m_type = Type.Rock;
                        if (m_attacks.size() <= 3) {
                            m_attacks.add(new RockThrow(this));
                            m_attacks.add(new Tackle(this));
                        }
                        break;
                }

                m_stats.getStatsFromFile(pokemonReader);

                canEvolve = pokemonReader.nextLine();
                m_canEvolve = canEvolve.equals("can evolve");

                m_statusCondition = StatusConditions.None;
                m_owner = owner;
                m_level = 1;
                pokemonReader.close();

        } catch(Exception e){
            System.out.println("Couldn't open the pokemon file");
            e.printStackTrace();
        }
        return this;
    }

    /*
    public void changeHP(float modifier){
        m_stats.setRemainingHp(m_stats.getHp() * modifier);
    }
    */
    public void changeHPRemaining(float damage){
        m_stats.setRemainingHp(m_stats.getRemainingHp() - damage);
    }

    /*
    public void changeAttack(float modifier){
        m_stats.setAttack(m_stats.getAttack() * modifier);
    }

    public void changeDefense(float modifier){
        m_stats.setDefense(m_stats.getDefense() * modifier);
    }

    public void changeSpeed(float modifier){
        m_stats.setSpeed(m_stats.getSpeed() * modifier);
    }


     */
    public float STAB(Attacks attack){
        if (attack.getM_moveType() == m_type){
            return 1.5f;
        }
        else{
            return 1.0f;
        }
    }

    public float effectiveness(Attacks attackUsed, Pokemon target){
        if (target.m_type.weakTo == attackUsed.getM_moveType()){
            return 2.0f;
        }
        else if (target.m_type.resistantTo == m_type){
            return 0.5f;
        }
        return 1.0f;
    }

    public int getDamageOfAnAttack(Pokemon target, Attacks attack){
        if (attack.getM_power() == 0){
            return 0;
        }
        return (int) Math.round(((((float) (2 * m_level) /5 + 2)
                * attack.getM_power() * m_stats.getAttack()/ target.m_stats.getDefense())
                /50 + 2) * STAB(attack) * effectiveness(attack, target));
    }

    /*
    public void attack(Pokemon target, Attacks attackUsed, int dmgDealt){
        target.changeHPRemaining(getDamageOfAnAttack(target, attackUsed));
        dmgDealt = getDamageOfAnAttack(target, attackUsed);
        return;
    }
    */


    public Attacks chooseAttack(){
        for (int i = 0; i < m_attacks.size(); i++){
            System.out.print((i + 1) + ". ");
            m_attacks.get(i).printAttack();
        }
        byte option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an attack");


        do {
            option = scanner.nextByte();
            switch (option){
            case 1:
                return m_attacks.get(0);
            case 2:
                return m_attacks.get(1);
            default:
                System.out.println("Invalid attack");
                break;
            }
        } while (option != 1 && option != 2);
        return null;
    }

    public void printPokemonInfo(){
        System.out.println(m_name);
        System.out.println("Hp remaining: " + m_stats.getRemainingHp());
        System.out.println("Type: " + m_type.getTypeName(this));
    }

    /*
    public boolean checkForLvlUp (){
        if (m_exp >= (int)((m_level * 0.75f) * (m_stats.getTotalStats() - m_level) * 0.75f)){
            return true;
        }
        return false;
    }

    public void gainEXP(int expToGain){
        m_exp = expToGain;
        while (checkForLvlUp()){
            m_level++;
            m_exp -= (int)((m_level * 0.75f) * (m_stats.getTotalStats() - m_level) * 0.75f) ;
        }
    }
    */
    public void applyingEffectDMG(Pokemon opposingPokemon){
        switch (m_statusCondition){
            case Burned:
                m_stats.setRemainingHp((int)(m_stats.getRemainingHp() - m_stats.getHp() * 0.2f));
                System.out.println(getName() + " was hurt by burn dmg");
                break;
            case Poisoned:
                m_stats.setRemainingHp((int)(m_stats.getRemainingHp() - m_stats.getHp() * 0.1f));
                System.out.println(getName() + " was hurt by poison dmg");
                break;
            case Seeded:
                opposingPokemon.m_stats.setRemainingHp((int)(opposingPokemon.m_stats.getRemainingHp() + m_stats.getHp() * 0.1f));
                m_stats.setRemainingHp(m_stats.getRemainingHp() - m_stats.getHp() * 0.2f);
                System.out.println(opposingPokemon.getName() + " revitalised from " + getName() + "'s health");
                break;
        }
    }

}
