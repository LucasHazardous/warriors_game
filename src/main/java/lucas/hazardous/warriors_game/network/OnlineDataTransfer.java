package lucas.hazardous.warriors_game.network;

import lucas.hazardous.warriors_game.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OnlineDataTransfer {
    public static PlayerClient playerClient;

    private static String hostname;
    private static int port;

    public static int[] onlineOpponentPosition = new int[2];
    public static int damageDelivered = 0;
    public static int onlinePlayerHealth = Constants.PLAYER_HEALTH;
    public static String nickname;

    public OnlineDataTransfer() {
        try {
            loadConnectionProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConnectionProperties() throws IOException {
        Properties connectionProperties = new Properties();
        connectionProperties.load(new FileInputStream("connection.properties"));

        hostname = connectionProperties.getProperty("host");
        port = Integer.parseInt(connectionProperties.getProperty("port"));
    }

    public static void loadNewPlayerClient() {
        reloadOnlineVariables();

        playerClient = new PlayerClient(hostname, port);
    }

    private static void reloadOnlineVariables() {
        onlineOpponentPosition = new int[2];
        damageDelivered = 0;
        onlinePlayerHealth = Constants.PLAYER_HEALTH;
        nickname = "";
    }

    public void sendInitializationData(String nickname) {
        playerClient.sendInitializationData(nickname);
    }

    public Thread createOnlineDataTransfer() {
        return new Thread(new GameDataReceiverLoop());
    }

    public void loadOpponentInitData() throws IOException {
        String receivedInitData = playerClient.readData();

        nickname = receivedInitData;
    }

    public void terminateConnection() throws IOException {
        playerClient.sendEndGameData();
        playerClient.close();
    }
}
