package lucas.hazardous.warriors_game;

import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.characters.OnlineWarrior;
import lucas.hazardous.warriors_game.characters.implementations.Warrior;
import lucas.hazardous.warriors_game.gui.MainWindow;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        OnlineDataTransfer onlineDataTransfer = new OnlineDataTransfer();

        CharacterCharacter localPlayer = new Warrior("CurrentPlayer", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, onlineDataTransfer.getPlayerClient());

        OnlineWarrior onlinePlayer = new OnlineWarrior();

        MainWindow mw = new MainWindow(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, localPlayer, onlinePlayer, onlineDataTransfer.createOnlineDataTransfer());
    }
}
