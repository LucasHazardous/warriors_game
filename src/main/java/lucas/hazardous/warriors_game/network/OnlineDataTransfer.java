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
    public static String nickname;

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

    public void sendInitializationData(String nickname) {
        playerClient.sendInitializationData(nickname, Constants.PLAYER_HEALTH);
    }

    public Thread createOnlineDataTransfer() {
        return new Thread(new GameDataReceiverLoop(playerClient));
    }

    public void loadOpponentInitData() throws IOException {
        String[] receivedInitData = playerClient.readData().split(" ");
        nickname = receivedInitData[0];
        onlinePlayerHealth = Integer.parseInt(receivedInitData[1]);
    }

    public PlayerClient getPlayerClient() {
        return playerClient;
    }
}
