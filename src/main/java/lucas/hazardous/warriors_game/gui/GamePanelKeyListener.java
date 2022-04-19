package lucas.hazardous.warriors_game.gui;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanelKeyListener extends KeyAdapter {
    private CharacterCharacter localPlayer;

    public GamePanelKeyListener(CharacterCharacter localPlayer) {
        this.localPlayer = localPlayer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();

            if (key == localPlayer.leftKey) {
                localPlayer.left();
            }

            if (key == localPlayer.rightKey) {
                localPlayer.right();
            }

            if (key == localPlayer.upKey) {
                localPlayer.up();
            }

            if (key == localPlayer.downKey) {
                localPlayer.down();
            }

            if (key == localPlayer.leftAttackKey) {
                localPlayer.setAttackLeftImage();

                attackNeighbourOnLeft(localPlayer);

                changeToBaseImageAfterDelay(localPlayer, 200);
            }

            if (key == localPlayer.rightAttackKey) {
                localPlayer.setAttackRightImage();

                attackNeighbourOnRight(localPlayer);

                changeToBaseImageAfterDelay(localPlayer, 200);
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
        if (player.getX() - Constants.CHARACTER_IMG_WIDTH == OnlineDataTransfer.onlineOpponentPosition[0]) {
            player.attackOpponent();
        }
    }

    private void attackNeighbourOnRight(CharacterCharacter player) {
        if (player.getX() + Constants.CHARACTER_IMG_WIDTH == OnlineDataTransfer.onlineOpponentPosition[0]) {
            player.attackOpponent();
        }
    }
}