package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.characters.LocalPlayer;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final LocalPlayer localPlayer;
    private final Player onlinePlayer;
    private final OnlineDataTransfer onlineDataTransfer;

    public MainWindow(int width, int height, LocalPlayer localPlayer, Player onlinePlayer, OnlineDataTransfer onlineDataTransfer) {
        this.localPlayer = localPlayer;
        this.onlinePlayer = onlinePlayer;
        this.onlineDataTransfer = onlineDataTransfer;

        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MenuPanel(this));
        setVisible(true);
        setFocusable(true);
    }

    protected void setLocalPlayerNickname(String nickname) {
        localPlayer.setName(nickname);
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
        Thread onlineConnectionThread = onlineDataTransfer.createOnlineDataTransfer();
        onlineConnectionThread.start();

        resetDamageDelivered();
        changeToGamePanel();
    }

    private void resetDamageDelivered() {
        OnlineDataTransfer.damageDelivered = 0;
    }

    private void changeToGamePanel() {
        getContentPane().removeAll();

        GamePanel gamePanel = new GamePanel(localPlayer, onlinePlayer, this);
        add(gamePanel);

        revalidateRepaint();

        gamePanel.requestFocus(false);
    }

    protected void changeGameToMainMenu() {
        getContentPane().removeAll();
        add(new MenuPanel(this));

        revalidateRepaint();
    }

    private void revalidateRepaint() {
        revalidate();
        repaint();
    }

    public void resetConnectionClient() throws IOException {
        onlineDataTransfer.terminateConnection();
    }

    public void showConnectionWarning() {
        JOptionPane.showMessageDialog(this, "Game was not ended properly.", "Connection ended", JOptionPane.WARNING_MESSAGE);
    }
}
