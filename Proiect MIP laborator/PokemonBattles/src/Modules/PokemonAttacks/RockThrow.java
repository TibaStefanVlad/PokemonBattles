package Modules.PokemonAttacks;

import Modules.*;

import java.util.Random;

public class RockThrow extends Attacks{
    public RockThrow(Pokemon user){
        super("Rock Throw", 45, Type.Rock, user);
    }

    private static final int effectChance = 10;
    private static final Random random = new Random();

    @Override
    public void applyEffect(Pokemon user, Pokemon target){
        Random random = new Random();
        int chanceToApplyEffect = random.nextInt(effectChance);

        if (chanceToApplyEffect == 2){
            target.m_statusCondition = StatusConditions.Confused;
            System.out.println(user.getName() + " applied " + StatusConditions.Confused + " effect on " + target.getName());
        }
    }
}
