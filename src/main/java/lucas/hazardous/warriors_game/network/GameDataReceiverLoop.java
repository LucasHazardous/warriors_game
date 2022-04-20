package lucas.hazardous.warriors_game.network;

import java.io.IOException;
import java.util.Objects;

import static lucas.hazardous.warriors_game.network.OnlineDataTransfer.*;

public class GameDataReceiverLoop implements Runnable {

    @Override
    public void run() {
        if(playerClient.isClosed())
            return;

        while (true) {
            try {
                String[] receivedData = playerClient.readData().split(" ");

                if (Objects.equals(receivedData[0], ""))
                    break;

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
