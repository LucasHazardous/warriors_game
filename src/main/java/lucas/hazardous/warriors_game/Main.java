package lucas.hazardous.warriors_game;

import lucas.hazardous.warriors_game.characters.implementations.OnlineWarrior;
import lucas.hazardous.warriors_game.gui.MainWindow;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

public class Main {
    public static void main(String[] args) {
        OnlineDataTransfer onlineDataTransfer = new OnlineDataTransfer();

        OnlineWarrior onlinePlayer = new OnlineWarrior();

        new MainWindow(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, onlinePlayer, onlineDataTransfer);
    }
}
