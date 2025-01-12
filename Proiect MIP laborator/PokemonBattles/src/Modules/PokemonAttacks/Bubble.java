package Modules.PokemonAttacks;

import Modules.Attacks;
import Modules.Pokemon;
import Modules.StatusConditions;
import Modules.Type;
import  java.util.Random;

public class Bubble extends Attacks {
    public Bubble(Pokemon user){
        super("Bubble", 15, Type.Water, user);
    }

    private static final int effectChance = 8;
    private static final Random random = new Random();

    @Override
    public void applyEffect(Pokemon user, Pokemon target){
        Random random = new Random();
        int chanceToApplyEffect = random.nextInt(effectChance);

        if (chanceToApplyEffect == 2){
            target.m_stats.setDefense(target.m_stats.getDefense() * 0.80f);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s defense with " + this.getM_attackName());
        }
    }
}
