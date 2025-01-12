package Interfaces;
import Modules.GameMode;
import Modules.Player;

import java.util.Scanner;

public interface IGameMode {

    public void setGameOption(GameMode playersOption);
    public GameMode getGameMode();
    public void selectGameMode(GameMode game);
    public void createPlayerSetup(Player newPlayer);
    public String returnPokemonFile(String fileName);

    void printGameModeHelp();

}
