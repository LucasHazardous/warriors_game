package lucas.hazardous.warriors_game.characters;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static lucas.hazardous.warriors_game.network.OnlineDataTransfer.playerClient;

public abstract class LocalPlayer implements Player {
    private int healthPoints = Constants.PLAYER_HEALTH;
    private AttackType attackType;
    protected String name, playerClass;
    private final int maxHealthPoints = Constants.PLAYER_HEALTH;
    public int leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey;

    private Image image, baseImage, attackLeftImage, attackRightImage;
    private int x, y;

    public LocalPlayer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.leftKey = leftKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftAttackKey = leftAttackKey;
        this.rightAttackKey = rightAttackKey;
        this.rightKey = rightKey;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else if (healthPoints > this.maxHealthPoints) {
            this.healthPoints = this.maxHealthPoints;
        }
        else {
            this.healthPoints = healthPoints;
        }
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAttackImages() {
        String baseImage = Constants.IMG_FOLDER + this.playerClass + "/base.png";
        String attackLeftImage = Constants.IMG_FOLDER + this.playerClass + "/left.png";
        String attackRightImage = Constants.IMG_FOLDER + this.playerClass + "/right.png";

        this.baseImage = new ImageIcon(baseImage).getImage();
        this.attackLeftImage = new ImageIcon(attackLeftImage).getImage();
        this.attackRightImage = new ImageIcon(attackRightImage).getImage();

        setBaseImage();
    }

    public void setBaseImage() {
        this.image = this.baseImage;
    }

    public void setAttackLeftImage() {
        this.image = this.attackLeftImage;
    }

    public void setAttackRightImage() {
        this.image = this.attackRightImage;
    }

    public abstract void left();

    public abstract void right();

    public abstract void up();

    public abstract void down();

    public void tryChangePosition(int newX, int newY) {
        if (OnlineDataTransfer.onlineOpponentPosition[0] != newX || OnlineDataTransfer.onlineOpponentPosition[1] != newY) {
            this.x = newX;
            this.y = newY;
            playerClient.sendGameData(newX, newY, 0, getHealthPoints());
        } else {
            reduceHealth(50);
        }
    }

    public void reduceHealth(int amount) {
        setHealthPoints(this.healthPoints - amount);
        playerClient.sendGameData(getX(), getY(), 0, getHealthPoints());
    }

    public void attackOpponent() {
        playerClient.sendGameData(getX(), getY(), Constants.ATTACK_STRENGTH, getHealthPoints());
    }
}
