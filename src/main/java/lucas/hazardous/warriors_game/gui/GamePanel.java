package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.characters.LocalPlayer;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static lucas.hazardous.warriors_game.Constants.*;

public class GamePanel extends JPanel implements ActionListener {
    private final Player[] players;
    private final Timer timer;
    private final LocalPlayer localPlayer;
    private final MainWindow mainWindow;

    public GamePanel(LocalPlayer localPlayer, Player onlinePlayer, MainWindow mainWindow) {
        this.players = new Player[]{localPlayer, onlinePlayer};
        this.localPlayer = localPlayer;
        this.mainWindow = mainWindow;

        setFocusable(true);
        addKeyListener(new GamePanelKeyListener(localPlayer));

        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);

        drawPlayers(g);
    }

    private void drawMap(Graphics g) {
        for(int i = 0; i < WINDOW_WIDTH/CHARACTER_IMG_WIDTH; i++) {
            for(int j = 0; j < WINDOW_HEIGHT/CHARACTER_IMG_HEIGHT; j++) {
                if(i%2 == 0 && j % 2 == 0)
                    g.setColor(Color.gray);
                else
                    g.setColor(Color.lightGray);
                g.fillRect(i*CHARACTER_IMG_WIDTH, j*CHARACTER_IMG_HEIGHT, CHARACTER_IMG_WIDTH, CHARACTER_IMG_HEIGHT);
            }
        }
    }

    private void drawPlayers(Graphics g) {
        for (Player player : players) {
            drawPlayerImage(g, player);

            drawPlayerHealthAmount(g, player);

            drawPlayerName(g, player);
        }
    }

    private void drawPlayerImage(Graphics g, Player player) {
        g.drawImage(player.getImage(), player.getX(), player.getY(), this);
    }

    private void drawPlayerHealthAmount(Graphics g, Player player) {
        g.setColor(Color.red);
        g.drawString(String.valueOf(player.getHealthPoints()), player.getX(), player.getY() + 12);
    }

    private void drawPlayerName(Graphics g, Player player) {
        g.setColor(Color.cyan);
        g.drawString(player.getName(), player.getX(), player.getY() + 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        reduceLocalPlayerHealth();

        endGameIfSomeoneDied();

        repaint();
    }

    private void reduceLocalPlayerHealth() {
        if(OnlineDataTransfer.damageDelivered != 0) {
            localPlayer.reduceHealth(OnlineDataTransfer.damageDelivered);
            OnlineDataTransfer.damageDelivered = 0;
        }
    }

    private void endGameIfSomeoneDied() {
        if(localPlayer.getHealthPoints() == 0 || OnlineDataTransfer.onlinePlayerHealth == 0) {
            timer.stop();
            try {
                mainWindow.resetConnectionClient();
            } catch (IOException ex) {
                mainWindow.showConnectionWarning();
            }
            mainWindow.changeGameToMainMenu();
        }
    }
}
