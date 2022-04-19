package lucas.hazardous.warriors_game.network;

import java.io.IOException;

import static lucas.hazardous.warriors_game.network.OnlineDataTransfer.*;

public class GameDataReceiverLoop implements Runnable {
    private PlayerClient playerClient;
    public GameDataReceiverLoop(PlayerClient playerClient) {
        this.playerClient = playerClient;
    }

    @Override
    public void run() {
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
    }
}
