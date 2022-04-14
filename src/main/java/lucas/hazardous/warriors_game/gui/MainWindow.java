package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(int width, int height, CharacterCharacter localPlayer, Player onlinePlayer) {
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GamePanel(localPlayer, onlinePlayer));
        setVisible(true);
    }
}
