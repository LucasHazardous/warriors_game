package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static lucas.hazardous.warriors_game.Constants.TIMER_DELAY;

public class GamePanel extends JPanel implements ActionListener {
    private Player[] players;
    private Timer timer;
    private CharacterCharacter localPlayer;

    public GamePanel(CharacterCharacter localPlayer, Player onlinePlayer) {
        this.players = new Player[]{localPlayer, onlinePlayer};
        this.localPlayer = localPlayer;

        setFocusable(true);
        addKeyListener(new GamePanelKeyListener(localPlayer));

        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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
        g.drawString(String.valueOf(player.getHealthPoints()), player.getX(), player.getY() + 12);
    }

    private void drawPlayerName(Graphics g, Player player) {
        g.drawString(player.getName(), player.getX(), player.getY() + 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(OnlineDataTransfer.damageDelivered != 0) {
            localPlayer.reduceHealth(OnlineDataTransfer.damageDelivered);
            OnlineDataTransfer.damageDelivered = 0;
        }

        repaint();
    }
}
