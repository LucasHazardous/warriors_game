package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.Main;
import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanelKeyListener extends KeyAdapter {
    private Player[] players;
    private CharacterCharacter player;

    public GamePanelKeyListener(Player[] players) {
        this.players = players;
        this.player = (CharacterCharacter) players[0];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();

            if (key == player.leftKey) {
                player.left();
            }

            if (key == player.rightKey) {
                player.right();
            }

            if (key == player.upKey) {
                player.up();
            }

            if (key == player.downKey) {
                player.down();
            }

            if (key == player.leftAttackKey) {
                player.setAttackLeftImage();

                attackNeighbourOnLeft(player);

                changeToBaseImageAfterDelay(player, 200);
            }

            if (key == player.rightAttackKey) {
                player.setAttackRightImage();

                attackNeighbourOnRight(player);

                changeToBaseImageAfterDelay(player, 200);
            }

    }

    private void changeToBaseImageAfterDelay(CharacterCharacter player, int delay) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        player.setBaseImage();
                    }
                }, delay
        );
    }

    private void attackNeighbourOnLeft(CharacterCharacter player) {
        if (player.getX() - Constants.CHARACTER_IMG_WIDTH == Main.opponentPosition[0]) {
            System.out.println("attacked opponent");
        }
    }

    private void attackNeighbourOnRight(CharacterCharacter player) {
        if (player.getX() + Constants.CHARACTER_IMG_WIDTH == Main.opponentPosition[1]) {
            System.out.println("attacked opponent");
        }
    }
}