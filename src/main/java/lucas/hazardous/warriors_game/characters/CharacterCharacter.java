package lucas.hazardous.warriors_game.characters;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;
import lucas.hazardous.warriors_game.network.PlayerClient;

import javax.swing.*;
import java.awt.*;

public abstract class CharacterCharacter implements CharacterBase, Player {
    private int healthPoints = 200;
    private AttackType attackType;
    private int attackAmount;
    protected String name, playerClass;
    private int maxHealthPoints;
    public int leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey;

    private PlayerClient playerClient;

    public CharacterCharacter(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, PlayerClient playerClient) {
        this.playerClient = playerClient;
        this.name = name;
        this.x = x;
        this.y = y;
        this.leftKey = leftKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftAttackKey = leftAttackKey;
        this.rightAttackKey = rightAttackKey;
        this.rightKey = rightKey;

        setMaxHealthPoints(Constants.PLAYER_HEALTH);
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

    public void setAttackAmount(int attackAmount) {
        this.attackAmount = attackAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public int getAttackAmount() {
        return attackAmount;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void attack(CharacterCharacter attackedPlayer) {
        attackedPlayer.reduceHealth(this.attackAmount);
    }

    @Override
    public void loseHealth(int amount) {
        setHealthPoints(this.getMaxHealthPoints() - amount);
    }

    @Override
    public void info() {
        System.out.println("Name: " + this.name + "\nCurrentHP: " + this.healthPoints);
    }

    private Image image, baseImage, attackLeftImage, attackRightImage;
    private int x, y;

    public void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void uploadImage() {
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
            playerClient.sendData(newX, newY, 0, getHealthPoints());
        } else {
            reduceHealth(50);
        }
    }

    public void reduceHealth(int amount) {
        setHealthPoints(this.healthPoints - amount);
        playerClient.sendData(getX(), getY(), 0, getHealthPoints());
    }

    public void attackOpponent() {
        playerClient.sendData(getX(), getY(), Constants.ATTACK_STRENGTH, getHealthPoints());
    }
}
