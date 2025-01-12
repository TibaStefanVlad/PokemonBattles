package Modules;

import java.util.Scanner;

public class Stats {
    private float m_hp;
    private float m_hpRemaining;
    private float m_attack;
    private float m_defense;
    private float m_speed;

    Stats(float hp, float attack, float defense, float speed){
        m_hp = hp;
        m_hpRemaining = hp;
        m_attack = attack;
        m_defense = defense;
        m_speed = speed;
    }

    Stats(){
        m_hp = 1;
        m_attack = 1;
        m_defense = 1;
        m_speed = 1;
        m_hpRemaining = m_hp;
    }


    public float getHp() {
        return m_hp;
    }

    public float getRemainingHp(){ return m_hpRemaining; }

    public void setRemainingHp(float remainingHp){
        m_hpRemaining = remainingHp;
    }

    public void setHp(float m_hp) { this.m_hp = m_hp; }

    public float getAttack() {
        return m_attack;
    }

    public void setAttack(float m_attack) {
        this.m_attack = m_attack;
    }

    public float getDefense() {
        return m_defense;
    }

    public void setDefense(float defense) {
        this.m_defense = defense;
    }

    public float getSpeed() {
        return m_speed;
    }

    public void setSpeed(float m_speed) {
        this.m_speed = m_speed;
    }

    public void getStatsFromFile(Scanner scannerName){
            String data;
            data = scannerName.nextLine();
            m_hp = Integer.parseInt(data);
            m_hpRemaining = m_hp;

            data = scannerName.nextLine();
            m_attack = Integer.parseInt(data);

            data = scannerName.nextLine();
            m_defense = Integer.parseInt(data);

            data = scannerName.nextLine();
            m_speed = Integer.parseInt(data);

    }

    public int getTotalStats(){
        return (int)(m_hp + m_attack + m_defense + m_speed);
    }

}
