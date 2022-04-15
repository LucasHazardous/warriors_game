package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;

public class MainWindow extends JFrame {
    private CharacterCharacter localPlayer;
    private Player onlinePlayer;
    private OnlineDataTransfer onlineDataTransfer;
    private Thread onlineConnectionThread;

    public MainWindow(int width, int height, CharacterCharacter localPlayer, Player onlinePlayer, OnlineDataTransfer onlineDataTransfer) {
        this.localPlayer = localPlayer;
        this.onlinePlayer = onlinePlayer;
        this.onlineDataTransfer = onlineDataTransfer;
        this.onlineConnectionThread = onlineDataTransfer.createOnlineDataTransfer();
        onlineConnectionThread.start();

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MenuPanel(this));
        setVisible(true);
    }

    protected void startOnlineGame() {
        onlineDataTransfer.sendInitializationData();

        if(OnlineDataTransfer.onlinePlayerHealth == Constants.PLAYER_HEALTH) {
            OnlineDataTransfer.damageDelivered = 0;
            addGamePanel();
        }
    }

    private void addGamePanel() {
        getContentPane().removeAll();

        GamePanel gamePanel = new GamePanel(localPlayer, onlinePlayer);
        add(gamePanel);

        this.setFocusable(true);
        this.revalidate();
        this.repaint();
        gamePanel.requestFocus(false);
    }
}
