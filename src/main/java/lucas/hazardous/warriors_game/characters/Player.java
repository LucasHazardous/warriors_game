package lucas.hazardous.warriors_game.characters;

import java.awt.*;

public interface Player {
    int getX();
    int getY();
    String getName();
    Image getImage();
    int getHealthPoints();
}
