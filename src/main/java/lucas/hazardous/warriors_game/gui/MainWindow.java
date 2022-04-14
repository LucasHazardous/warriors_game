package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;

import javax.swing.*;

public class MainWindow extends JFrame {
    private CharacterCharacter localPlayer;
    private Player onlinePlayer;
    private Thread onlineConnectionThread;

    public MainWindow(int width, int height, CharacterCharacter localPlayer, Player onlinePlayer, Thread onlineConnectionThread) {
        this.localPlayer = localPlayer;
        this.onlinePlayer = onlinePlayer;
        this.onlineConnectionThread = onlineConnectionThread;

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MenuPanel(this));
        setVisible(true);
    }

    public void startOnlineGame() {
        getContentPane().removeAll();
        onlineConnectionThread.start();
        GamePanel gamePanel = new GamePanel(localPlayer, onlinePlayer);
        add(gamePanel);
        this.setFocusable(true);
        this.revalidate();
        this.repaint();
        gamePanel.requestFocus(false);
    }
}
