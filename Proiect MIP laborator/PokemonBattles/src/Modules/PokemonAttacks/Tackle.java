package Modules.PokemonAttacks;

import Modules.Attacks;
import Modules.Pokemon;
import Modules.StatusConditions;
import Modules.Type;

public class Tackle extends Attacks {
    public Tackle(Pokemon user) { super("Tackle", 25, Type.Normal, user); }

    @Override
    public void applyEffect(Pokemon user, Pokemon target) {
    }
}
