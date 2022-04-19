package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    private CharacterCharacter localPlayer;
    private Player onlinePlayer;
    private Thread onlineConnectionThread;
    private OnlineDataTransfer onlineDataTransfer;

    public MainWindow(int width, int height, CharacterCharacter localPlayer, Player onlinePlayer, OnlineDataTransfer onlineDataTransfer) {
        this.localPlayer = localPlayer;
        this.onlinePlayer = onlinePlayer;
        this.onlineDataTransfer = onlineDataTransfer;
        this.onlineConnectionThread = onlineDataTransfer.createOnlineDataTransfer();

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MenuPanel(this));
        setVisible(true);
    }

    protected void sendInitializationData(String nickname) {
        onlineDataTransfer.sendInitializationData(nickname);
    }

    protected void receiveOnlineData() {
        try {
            onlineDataTransfer.loadOpponentInitData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void startOnlineGame() {
        onlineConnectionThread.start();
        resetDamageDelivered();
        changeToGamePanel();
    }

    private void resetDamageDelivered() {
        OnlineDataTransfer.damageDelivered = 0;
    }

    private void changeToGamePanel() {
        getContentPane().removeAll();

        GamePanel gamePanel = new GamePanel(localPlayer, onlinePlayer);
        add(gamePanel);

        this.setFocusable(true);
        this.revalidate();
        this.repaint();
        gamePanel.requestFocus(false);
    }
}
