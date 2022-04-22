package lucas.hazardous.warriors_game.characters;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static lucas.hazardous.warriors_game.network.OnlineDataTransfer.playerClient;

public abstract class LocalPlayer implements Player {
    private int healthPoints = Constants.PLAYER_HEALTH;
    protected String name, playerClass;
    public int leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey;

    private Image image, baseImage, attackLeftImage, attackRightImage;
    private int x, y;

    public LocalPlayer(int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
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
        int maxHealthPoints = Constants.PLAYER_HEALTH;
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else if (healthPoints > maxHealthPoints) {
            this.healthPoints = maxHealthPoints;
        }
        else {
            this.healthPoints = healthPoints;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
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

    public void setAttackImages() throws IOException {
        System.out.println(Constants.IMG_FOLDER + this.playerClass + "/base.png");
        this.baseImage = ImageIO.read(getImagePath(Constants.IMG_FOLDER + this.playerClass + "/base.png"));
        this.attackLeftImage = ImageIO.read(getImagePath(Constants.IMG_FOLDER + this.playerClass + "/left.png"));
        this.attackRightImage = ImageIO.read(getImagePath(Constants.IMG_FOLDER + this.playerClass + "/right.png"));

        setBaseImage();
    }

    public InputStream getImagePath(String image) {
        return Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(image));
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
