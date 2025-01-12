package Modules.PokemonAttacks;

import Modules.Attacks;
import Modules.Pokemon;
import Modules.StatusConditions;
import Modules.Type;

public class LeechSeed extends Attacks{
    public LeechSeed(Pokemon user) {
    super("LeechSeed", 0, Type.Grass, user);
    }

    @Override
    public void applyEffect(Pokemon user, Pokemon target) {
        target.m_statusCondition = StatusConditions.Seeded;
        System.out.println(user.getName() + " applied " + StatusConditions.Seeded + " effect on " + target.getName());
    }


}
