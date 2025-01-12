package Modules.PokemonAttacks;

import Modules.Attacks;
import Modules.Pokemon;
import Modules.StatusConditions;
import Modules.Type;

import java.util.Random;

public class Ember extends Attacks {
    public Ember(Pokemon user) {
        super("Ember", 30, Type.Fire, user);
    }

    private static final int effectChance = 12;

    @Override
    public void applyEffect(Pokemon user, Pokemon target) {
        Random random = new Random();
        int chanceToApplyEffect = random.nextInt(effectChance);

        if (chanceToApplyEffect == 2){
            target.m_statusCondition = StatusConditions.Burned;
            System.out.println(user.getName() + " applied " + StatusConditions.Burned + " effect on " + target.getName());
        }
    }
}
