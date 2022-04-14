package lucas.hazardous.warriors_game.characters;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.Player;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.swing.*;
import java.awt.*;

public class OnlineWarrior implements Player {
    private Image image = new ImageIcon(Constants.IMG_FOLDER + "archer/base.png").getImage();

    @Override
    public int getX() {
        return OnlineDataTransfer.onlineOpponentPosition[0];
    }

    @Override
    public int getY() {
        return OnlineDataTransfer.onlineOpponentPosition[1];
    }

    @Override
    public String getName() {
        return "online";
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int getHealthPoints() {
        return OnlineDataTransfer.onlinePlayerHealth;
    }
}
