package lucas.hazardous.warriors_game.characters;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.Main;
import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.PlayerClient;

import javax.swing.*;
import java.awt.*;

public abstract class CharacterCharacter implements CharacterBase, Player {
    private int healthPoints = 200;
    private int manaPoints;
    private int level;
    private AttackType attackType;
    private int attackAmount;
    protected String name, playerClass;
    private int maxHealthPoints;
    private int maxManaPoints;
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
        this.setLevel(1);
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

    public void setManaPoints(int manaPoints) {
        if (manaPoints < 0) {
            this.manaPoints = 0;
        } else if (manaPoints > this.maxManaPoints) {
            this.manaPoints = this.maxManaPoints;
        }
        else {
            this.manaPoints = manaPoints;
        }
    }

    public void setLevel(int level) {
        if (level < 1) {
            System.out.println("We can't lose level");
        } else {
            this.level = level;
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

    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public int getLevel() {
        return level;
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

    public int getMaxManaPoints() {
        return maxManaPoints;
    }


    public void attack(CharacterCharacter attackedPlayer) {
        attackedPlayer.reduceHealth(this.attackAmount);
    }

    @Override
    public void restoreHealth(int amount) {
        setHealthPoints(this.getMaxHealthPoints() + amount);
    }

    @Override
    public void loseHealth(int amount) {
        setHealthPoints(this.getMaxHealthPoints() - amount);
    }

    @Override
    public void restoreMana(int amount) {
        setManaPoints(this.getMaxManaPoints() + amount);
    }

    @Override
    public void loseMana(int amount) {
        setManaPoints(this.getMaxManaPoints() - amount);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
    }

    @Override
    public void info() {
        System.out.println("Name: " + this.name + "\nCurrentHP: " + this.healthPoints + "\nCurrentmana: " + this.maxManaPoints + "\nLevel: " + this.level);
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

    public abstract void leftAttack();

    public abstract void rightAttack();

    public void tryChangePosition(int newX, int newY) {
        if (Main.opponentPosition[0] != newX || Main.opponentPosition[1] != newY) {
            this.x = newX;
            this.y = newY;
            playerClient.sendData(newX + " " + newY);
        } else {
            reduceHealth(50);
        }
    }

    private void reduceHealth(int amount) {
        setHealthPoints(this.healthPoints - amount);
    }
}
