package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Player[] players;
    private static final int TIMER_DELAY = 100;
    private Timer timer;

    public GamePanel(CharacterCharacter localPlayer, Player onlinePlayer) {
        this.players = new Player[]{localPlayer, onlinePlayer};

        setFocusable(true);
        addKeyListener(new GamePanelKeyListener(players));

        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Player player : players) {
            drawPlayerImage(g, player);

            drawPlayerHealthAmount(g, player);

            drawPlayerManaAmount(g, player);
        }
    }

    private void drawPlayerImage(Graphics g, Player player) {
        g.drawImage(player.getImage(), player.getX(), player.getY(), this);
    }

    private void drawPlayerHealthAmount(Graphics g, Player player) {
        g.drawString("" + player.getHealthPoints(), player.getX(), player.getY() + 12);
    }

    private void drawPlayerManaAmount(Graphics g, Player player) {
        g.drawString("0", player.getX(), player.getY() + 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
