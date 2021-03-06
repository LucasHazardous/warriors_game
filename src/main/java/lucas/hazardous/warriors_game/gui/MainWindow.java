package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.characters.LocalPlayer;
import lucas.hazardous.warriors_game.characters.implementations.Archer;
import lucas.hazardous.warriors_game.characters.implementations.Warrior;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import static lucas.hazardous.warriors_game.Constants.*;

public class MainWindow extends JFrame {
    private LocalPlayer localPlayer;
    private final Player onlinePlayer;
    private final OnlineDataTransfer onlineDataTransfer;
    private String arenaChoice;
    private String heroChoice;

    public MainWindow(Player onlinePlayer, OnlineDataTransfer onlineDataTransfer) {
        this.onlinePlayer = onlinePlayer;
        this.onlineDataTransfer = onlineDataTransfer;

        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new MenuPanel(this));
        setVisible(true);
        setFocusable(true);
        setResizable(false);

        setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void setArenaChoice(String arenaChoice) {
        this.arenaChoice = arenaChoice;
    }

    public void setHeroChoice(String heroChoice) {
        this.heroChoice = heroChoice;
    }

    public void generateLocalPlayer() {
        switch (heroChoice) {
            default:
            case "Warrior":
                localPlayer = new Warrior(generateStartPosition(Constants.WINDOW_WIDTH, Constants.CHARACTER_IMG_WIDTH), generateStartPosition(Constants.WINDOW_HEIGHT, Constants.CHARACTER_IMG_HEIGHT), KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
                break;
            case "Archer":
                localPlayer = new Archer(generateStartPosition(Constants.WINDOW_WIDTH, Constants.CHARACTER_IMG_WIDTH), generateStartPosition(Constants.WINDOW_HEIGHT, Constants.CHARACTER_IMG_HEIGHT), KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
                break;
        }

    }

    private static int generateStartPosition(int rangeBound, int tile) {
        Random random = new Random();
        return random.nextInt(rangeBound/tile)*tile;
    }

    protected void setLocalPlayerNickname(String nickname) {
        localPlayer.setName(nickname);
    }

    protected void sendInitializationData(String nickname) {
        OnlineDataTransfer.playerClient.sendInitializationData(nickname, localPlayer.getX(), localPlayer.getY());
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

        GamePanel gamePanel = new GamePanel(localPlayer, onlinePlayer, this, arenaChoice);
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
