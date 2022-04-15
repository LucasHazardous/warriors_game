package lucas.hazardous.warriors_game.network;

import lucas.hazardous.warriors_game.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OnlineDataTransfer {
    private Properties connectionProperties;
    private PlayerClient playerClient;
    public static int[] onlineOpponentPosition = new int[2];
    public static int damageDelivered = 0;
    public static int onlinePlayerHealth = 1;

    public OnlineDataTransfer() {
        try {
            loadConnectionProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playerClient = new PlayerClient(connectionProperties.getProperty("host"), Integer.parseInt(connectionProperties.getProperty("port")));
    }

    private void loadConnectionProperties() throws IOException {
        connectionProperties = new Properties();
        connectionProperties.load(new FileInputStream("connection.properties"));
    }

    public void sendInitializationData() {
        playerClient.sendData(0, 0, 0, Constants.PLAYER_HEALTH);
    }

    public Thread createOnlineDataTransfer() {
        return new Thread(() -> {
            while(true) {
                try {
                    String[] receivedData = playerClient.readData().split(" ");
                    onlineOpponentPosition[0] = Integer.parseInt(receivedData[0]);
                    onlineOpponentPosition[1] = Integer.parseInt(receivedData[1]);
                    damageDelivered = Integer.parseInt(receivedData[2]);
                    onlinePlayerHealth = Integer.parseInt(receivedData[3]);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
    }

    public PlayerClient getPlayerClient() {
        return playerClient;
    }
}
