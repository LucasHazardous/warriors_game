package lucas.hazardous.warriors_game;

import javax.swing.*;
import java.awt.*;

public class OnlineWarrior implements Player {
    private Image image = new ImageIcon(Constants.IMG_FOLDER + "archer/base.png").getImage();

    @Override
    public int getX() {
        return Main.opponentPosition[0];
    }

    @Override
    public int getY() {
        return Main.opponentPosition[1];
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
        return 100;
    }
}
