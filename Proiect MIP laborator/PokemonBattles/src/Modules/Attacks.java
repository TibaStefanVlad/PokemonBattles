package Modules;

public abstract class Attacks {
    private String m_attackName;
    private int m_power;
    private Type m_moveType;
    protected Pokemon m_user;

    public Attacks(String attackName, int damage, Type moveType, Pokemon user){
        m_attackName = attackName;
        m_power = damage;
        m_moveType = moveType;
        m_user = user;
    }

    public Attacks(){
        m_attackName = new String();
        m_power = 0;
        m_user = null;
    }

    public Attacks(Attacks other){
        m_attackName = other.m_attackName;
        m_power = other.m_power;
        m_moveType = other.m_moveType;
        m_user = other.m_user;
    }

    public String getM_attackName() {
        return m_attackName;
    }

    public void setM_attackName(String m_attackName) {
        this.m_attackName = m_attackName;
    }

    public int getM_power() {
        return m_power;
    }

    public void setM_power(int m_power) {
        this.m_power = m_power;
    }

    public Type getM_moveType() {
        return m_moveType;
    }

    public void setM_moveType(Type m_moveType) {
        this.m_moveType = m_moveType;
    }

    public Pokemon getUser(){
        return m_user;
    }

    public void setUser(Pokemon otherUser){
        m_user = otherUser;
    }

    public abstract void applyEffect(Pokemon user, Pokemon target);

    public void printAttack(){
        System.out.println(m_attackName);
        return;
    }
}
