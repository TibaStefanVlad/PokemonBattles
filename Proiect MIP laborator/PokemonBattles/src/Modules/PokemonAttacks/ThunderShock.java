package Modules.PokemonAttacks;

import Modules.Attacks;
import Modules.Pokemon;
import Modules.StatusConditions;
import Modules.Type;
import  java.util.Random;

public class ThunderShock extends Attacks {
    public ThunderShock(Pokemon user){
        super("Thunder Shock", 30, Type.Electric, user);
    }

    private static final int effectChance = 9;
    private static final Random random = new Random();

    @Override
    public void applyEffect(Pokemon user, Pokemon target){
        Random random = new Random();
        int chanceToApplyEffect = random.nextInt(effectChance);

        if (chanceToApplyEffect == 2){
            target.m_statusCondition = StatusConditions.Paralyzed;
            System.out.println(user.getName() + " applied " + StatusConditions.Paralyzed + " effect on " + target.getName());
        }
    }

}
