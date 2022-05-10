package lucas.hazardous.warriors_game.characters.implementations;

import lucas.hazardous.warriors_game.Constants;
import lucas.hazardous.warriors_game.characters.Player;
import lucas.hazardous.warriors_game.network.OnlineDataTransfer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class OnlineWarrior implements Player {
    private Image image;

    {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(Constants.IMG_FOLDER + "online_enemy/base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        return OnlineDataTransfer.nickname;
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
