package lucas.hazardous.warriors_game.characters.implementations;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.characters.LocalPlayer;

import java.io.IOException;

public class Warrior extends LocalPlayer {
   public Warrior(int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);

        this.playerClass = "warrior";
        this.setHealthPoints(200);
       try {
           this.setAttackImages();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void left() {
        int newPositionX = this.getX() > Constants.CHARACTER_IMG_WIDTH ? this.getX() - Constants.CHARACTER_IMG_WIDTH : 0;
        tryChangePosition(newPositionX, getY());
    }

    @Override
    public void right() {
        int newPositionX = this.getX() < Constants.MAX_RIGHT_POSITION ? this.getX() + Constants.CHARACTER_IMG_WIDTH : Constants.MAX_RIGHT_POSITION;
        tryChangePosition(newPositionX, getY());
    }

    @Override
    public void up() {
        int newPositionY = this.getY() > Constants.CHARACTER_IMG_HEIGHT ? this.getY() - Constants.CHARACTER_IMG_HEIGHT : 0;
        tryChangePosition(getX(), newPositionY);
    }

    @Override
    public void down() {
        int newPositionY = this.getY() < Constants.MAX_RIGHT_POSITION ? this.getY() + Constants.CHARACTER_IMG_HEIGHT : Constants.MAX_RIGHT_POSITION;
        tryChangePosition(getX(), newPositionY);
    }
}
