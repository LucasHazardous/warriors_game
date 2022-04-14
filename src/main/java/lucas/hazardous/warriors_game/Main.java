package lucas.hazardous.warriors_game;

import lucas.hazardous.warriors_game.characters.CharacterCharacter;
import lucas.hazardous.warriors_game.characters.implementations.Warrior;
import lucas.hazardous.warriors_game.gui.MainWindow;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static int[] opponentPosition = new int[2];

    public static void main(String[] args) {
        Properties connectionProperties = new Properties();
        try {
            connectionProperties.load(new FileInputStream("connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayerClient playerClient = new PlayerClient(connectionProperties.getProperty("host"), Integer.parseInt(connectionProperties.getProperty("port")));
        playerClient.sendData("0 0");

        Thread onlineDataReader = new Thread(() -> {
            while(true) {
                try {
                    String[] receivedData = playerClient.readData().split(" ");
                    opponentPosition[0] = Integer.parseInt(receivedData[0]);
                    opponentPosition[1] = Integer.parseInt(receivedData[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        onlineDataReader.start();

        CharacterCharacter localPlayer = new Warrior("CurrentPlayer", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, playerClient);
        OnlineWarrior onlinePlayer = new OnlineWarrior();
        MainWindow mw = new MainWindow(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, localPlayer, onlinePlayer);
    }
}
