package Modules;

public enum Type {
    Water,
    Fire,
    Grass,
    Normal,
    Rock,
    Electric;

    public String name;
    public Type weakTo;
    public Type resistantTo;

    static{
        Water.weakTo = Grass;
        Water.resistantTo = Fire;

        Fire.weakTo = Water;
        Fire.resistantTo = Grass;

        Grass.weakTo = Fire;
        Grass.resistantTo = Water;

        Normal.weakTo = Rock;
        Normal.resistantTo = Grass;

        Rock.weakTo = Water;
        Rock.resistantTo = Normal;

        Electric.weakTo = Rock;
        Electric.resistantTo = Electric;
    }

    public String getTypeName(Pokemon currentPokemon){
        switch (currentPokemon.getType()){
            case Water:
                return "Water";
            case Fire:
                return "Fire";
            case Grass:
                return "Grass";
            case Normal:
                return "Normal";
            case Rock:
                return "Rock";
            case Electric:
                return "Electric";
        }
        return " ";
    }
}
