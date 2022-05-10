package lucas.hazardous.warriors_game;

import java.awt.*;
import java.util.HashMap;

public class Constants {
    public static final int WINDOW_HEIGHT = 400;
    public static final int WINDOW_WIDTH = 400;
    public static final int CHARACTER_IMG_WIDTH = 40;
    public static final int CHARACTER_IMG_HEIGHT = 80;
    public static final int MAX_RIGHT_POSITION = 320;

    public static final String WINDOW_TITLE = "Warriors Game";

    public static final HashMap<String, Color[]> ARENA_LIST = new HashMap<>();

    public static final String[] HERO_LIST = new String[]{"Warrior", "Archer"};

    public static final String IMG_FOLDER = "images/";

    public static final int PLAYER_HEALTH = 200;

    public static final int ATTACK_STRENGTH = 20;

    public static final int TIMER_DELAY = 100;

    static
    {
        ARENA_LIST.put("Wastelands", new Color[]{new Color(229, 223, 136), new Color(198, 171, 114)});
        ARENA_LIST.put("Ocean", new Color[]{new Color(214, 236, 239), new Color(157, 215, 223)});
    }
}
