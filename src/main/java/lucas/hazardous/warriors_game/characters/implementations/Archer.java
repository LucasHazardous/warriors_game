package lucas.hazardous.warriors_game.characters.implementations;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.PlayerClient;
import lucas.hazardous.warriors_game.characters.AttackType;
import lucas.hazardous.warriors_game.characters.CharacterCharacter;

public class Archer  extends CharacterCharacter {
    public Archer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, PlayerClient player) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, player);

        this.setAttackType(AttackType.PHYSICAL_RANGED);
        this.setMaxHealthPoints(300);
        this.setManaPoints(300);
        this.playerClass = "archer";
        this.uploadImage();
        this.setAttackAmount(70);
    }
    @Override
    public void left() {
        int newPositionX = this.getX() >= Constants.CHARACTER_IMG_WIDTH ? this.getX() - Constants.CHARACTER_IMG_WIDTH : Constants.MAX_RIGHT_POSITION;
        tryChangePosition(newPositionX, getY());

    }

    @Override
    public void right() {
        int newPositionX = this.getX() < Constants.MAX_RIGHT_POSITION ? this.getX() + Constants.CHARACTER_IMG_WIDTH : 0;
        tryChangePosition(newPositionX, getY());
    }

    @Override
    public void up() {
        int newPositionY = this.getY() < Constants.CHARACTER_IMG_HEIGHT ? Constants.MAX_RIGHT_POSITION : this.getY() - Constants.CHARACTER_IMG_HEIGHT;
        tryChangePosition(getX(), newPositionY);
    }

    @Override
    public void down() {
        int newPositionY = this.getY() < Constants.MAX_RIGHT_POSITION ? this.getY() + Constants.CHARACTER_IMG_HEIGHT : 0;
        tryChangePosition(getX(), newPositionY);
    }
    public void leftAttack() {

    }
    public void rightAttack() {

    }
}
